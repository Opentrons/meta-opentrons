# pipenv_app_bundle.bbclass: Install python applications described by
# pipenv projects as directories in /opt (or anywhere, really)

inherit distutils3-base

DEPENDS += "python3 python3-native python3-pip-native python3-micropipenv-native "
RDEPENDS_${PN} += " python3 python3-modules"

# Whether pipenv or poetry is the appropriate underlying dependency manager
# parse
PIPENV_APP_BUNDLE_PACKAGE_SOURCE ??= "pipenv"

# This should contain a list of python dependencies that should not be
# installed in the separate directory.  This should be done for packages
# that have significant native code extensions that need special handling
# in openembedded's build system, like numpy. These packages should be
# marked by the recipe as dependencies separately, and their versions will
# have to be handled manually at that level.
PIPENV_APP_BUNDLE_USE_GLOBAL ??= ""
# Add extra packages that might not be captured by the Pipfile.lock (for the
# same reason behind PIPENV_APP_BUNDLE_USE_GLOBAL) and should be injected
# into the requirements
PIPENV_APP_BUNDLE_EXTRAS ??= ""
# This is where the root of the project (i.e. the directory of the Pipfile)
# is
PIPENV_APP_BUNDLE_PROJECT_ROOT ??= "${S}"
# The install directory on the target
PIPENV_APP_BUNDLE_DIR ??= "/opt/${PN}"
# The version of pipenv with which the current lockfiles were generated
# does not capture certain transitive dependencies. When we use micropipenv
# to generate a pip requirements file from a lockfile, it will (unless we
# ask it not to) add the hashes from the lockfile. If the transitive
# dependencies aren't _in_ the lockfile, they won't be in the requirements,
# and pip will install them at runtime, but they won't have hashes or pinned
# versions, and since pip either installs everything in hashes mode or nothing
# in hashes mode, it breaks install.
# Until any given subproject's Pipfile.lock is regenerated with a modern Pipenv
# (the current version counts) the problem will continue, and recipes using
# those lockfiles should set this to "yes".
PIPENV_APP_BUNDLE_STRIP_HASHES ??= "no"
PIPENV_APP_BUNDLE_SOURCE_VENV := "${B}/build-venv"


PIP_ENVARGS := " \
   STAGING_INCDIR=${STAGING_INCDIR} \
   STAGING_LIBDIR=${STAGING_LIBDIR} \
   _PYTHON_SYSCONFIGDATA_NAME=_sysconfigdata__linux_x86_64-linux-gnu \
"

python do_rewrite_requirements() {
    # as-is, the requirements.txt generated from pip freeze has two problems with
    # how it encodes the monorepo dependency sections. first, they're editable,
    # which means they'll be in src/ somewhere; and second, they're going to have
    # their sources set to github references, so running pip install will download
    # the monorepo an extra time for each internal dep (and we already have it once!)
    # we're going to rewrite it to make the references to the monorepo files non-editable
    # and relative to a file directory locally
    reqsfile = d.getVar("B") + '/requirements-unfiltered.txt'
    orig = open(reqsfile).read().split('\n')
    condensed = []
    working = ''
    for line in orig:
        if not line.endswith('\\'):
            working += line.strip()
            condensed.append(working)
            working = ''
        else:
            working += line.strip()[:-1] + ' '
    if working: condensed.append(working)
    extras = d.getVar("PIPENV_APP_BUNDLE_EXTRAS")
    if extras:
        if ' ' in extras:
            condensed.extend(extras.split(' '))
        else:
            condensed.append(extras)
    internal = d.getVar("B") + '/requirements-condensed.txt'
    open(internal, 'w').write('\n'.join(condensed))
    stripped = [l for l in condensed if not l.strip().startswith('#')]
    pypi_outfile = d.getVar("B") + '/pypi.txt'
    local_outfile = d.getVar("B") + '/local.txt'
    pypi = []
    local = []
    for line in stripped:
        if not line: continue
        if ' ' in line:
             plainname = line.split(' ')[0]
        else:
             plainname = line
        bb.debug(1, 'Checking ' + plainname)

        if line.startswith('--index-url'): pypi.append(line)
        elif line.startswith('--editable') or line.startswith('./'):
            # an editable probably-local package
            if line.startswith('--editable'):
                working = line.split('--editable')[-1].strip()
            else:
                working = line.strip()
            if not working.startswith('./'):
                bb.debug(1, 'Skipping {}'.format(line))
                continue
            working = d.getVar('PIPENV_APP_BUNDLE_PROJECT_ROOT') + '/' + working
            local.append(working)
            bb.debug(1, 'Rewrote local path to ' + working)
        elif not line.startswith('.') and not '://' in line:
            # This is a package from pypi; check if it's global
            first_nonalpha = [c for c in line if c in '=~^<>']
            pkgname = line.split(first_nonalpha[0])[0] if first_nonalpha else line
            if pkgname in d.getVar('PIPENV_APP_BUNDLE_USE_GLOBAL'):
                bb.debug(1, 'Using global version of {}'.format(pkgname))
                continue
            else:
                bb.debug(1, 'Keeping {}'.format(line))
                pypi.append(line)
        else:
            bb.debug(1, 'Keeping ' + line)
            pypi.append(line)
    open(pypi_outfile, 'w').write('\n'.join(pypi) + '\n')
    open(local_outfile, 'w').write('\n'.join(local) + '\n')
}

do_rewrite_requirements[vardeps] += " PIPENV_APP_BUNDLE_USE_GLOBAL PIPENV_APP_BUNDLE_EXTRAS "

addtask do_rewrite_requirements after do_configure before do_compile

do_configure_prepend () {
   cd ${PIPENV_APP_BUNDLE_PROJECT_ROOT}
   bbplain "Running micropipenv in ${PIPENV_APP_BUNDLE_PROJECT_ROOT}"
   if [[ "${PIPENV_APP_BUNDLE_STRIP_HASHES}" = "no" ]] ; then
       HASHES=
   else
       HASHES="--no-hashes"
   fi
   ${PYTHON} -m micropipenv requirements --method ${PIPENV_APP_BUNDLE_PACKAGE_SOURCE} --no-dev ${HASHES} > ${B}/requirements-unfiltered.txt
}

do_configure[vardeps] += "PIPENV_APP_BUNDLE_STRIP_HASHES PIPENV_APP_BUNDLE_PROJECT_ROOT"

PIP_ARGS := "--no-compile \
             --no-binary :all: \
             --progress-bar off \
             --force-reinstall \
             -t ${PIPENV_APP_BUNDLE_SOURCE_VENV}"

do_compile () {
   ${PIP_ENVARGS} ${PYTHON} -m pip install \
      -r ${B}/pypi.txt \
      ${PIP_ARGS}

   ${PIP_ENVARGS} ${PYTHON} -m pip install \
      -r ${B}/local.txt \
      ${PIP_ARGS} \
      --no-deps \
      --use-feature=in-tree-build

   ${PIP_ENVARGS} ${PYTHON} -m pip install \
      ${PIPENV_APP_BUNDLE_PROJECT_ROOT} \
      --use-feature=in-tree-build \
      --no-deps \
      ${PIP_ARGS}
}

do_compile[dirs] += " ${PIPENV_APP_BUNDLE_SOURCE_VENV}"

do_install () {
   cd ${PIPENV_APP_BUNDLE_SOURCE_VENV}
   install -d ${D}${PIPENV_APP_BUNDLE_DIR}
   find . -type d -not -wholename ./bin* -not -wholename ./Misc*  \
        -exec install -d "${D}${PIPENV_APP_BUNDLE_DIR}/{}" \;
   find . -type f -not -wholename ./bin/**/* -not -wholename ./Misc/**/* \
        -exec install "{}" "${D}${PIPENV_APP_BUNDLE_DIR}/{}" \;
}

FILES_${PN} = "${PIPENV_APP_BUNDLE_DIR}"
