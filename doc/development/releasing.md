# Releasing

Currently, the releasing is performed using the git tags and artifacts are
uploaded to the [Github releases][gh-releases].

## Pre release process

Update the `doc/changes/changelog.md` and `doc/changes/changes_VERSION.md` files
with the summary of all the changes since the last release. 

Please make sure to update any other necessary files, for example, `README.md`
or user guide with new features or updated new versions.

## Releasing steps

Follow these steps in order to create a new release:

- Run `./scripts/ci.sh` and make sure everything is working.
- Add a git tag, for example, `git tag -a 0.4.4 -m "Release version 0.4.4"`.
- Push tags to remote, `git push --tags`.

Please make sure that the new version tag follows the [Semantic Versioning
2.0.0](https://semver.org/).

The next Travis CI run will detect the new tag and create a new Github release
and publish the artifacts.

## Post release process

After the release process, the new [Github release][gh-releases] notes should be
added. It should be same as the pre-release update to the
`doc/changes/changes_VERSIONS.md.md` file.

Click on the "Edit release" button on the latest release version on the Github
releases page, and add the release notes.

## Using Release Robot

TODO

[gh-releases]: https://github.com/exasol/kafka-connector-extension/releases
