# Change Log
All notable changes to this project will be documented in this file, which follows the guidelines
on [Keep a CHANGELOG](http://keepachangelog.com/). This project adheres to
[Semantic Versioning](http://semver.org/).

## [17.0.1] - 2023-07-12
### Updated
- Update Wiremock to 3.0.0-beta-10
- Update junit5

## [17.0.0] - 2023-05-25
### Updated
- Update Wiremock to 2.27.2
- Update to Java 17
- Update integration tests to use Wildfly 26.1.2.Final
- Update Wildfly-Maven-Plugin to 4.0.0.Final
### Changed
- Bumped version to 17.0.0 to match Java and Framework versions

## [2.0.0] - 2018-11-24
### Updated
- update wiremock dependency to 2.0.19
- updated travis to new build processes

### Changed
- BREAKING CHANGES for proxy behaviour
  This is why the version is bumped to 2.0.0
- Additional changes to get this working by Shirly Tarboton

## [1.3.0] - 2017-07-04

### Added
- Static PDF file  (sample.pdf) to wiremock-service so that it can be served as streaming content

## [1.2.0] - 2016-08-08

### Updated
- Update Wiremock to the forked version able to work behind a http proxy

## [1.1.0] - 2016-08-08

### Added
- Test utils module containing stub helper for internal endpoints - currently only ping

## [1.0.0] - 2016-06-09
### Added
- Initial implementation of the WireMock service
