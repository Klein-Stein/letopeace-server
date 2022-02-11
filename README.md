Letopeace server
=========

An open-source server for Letopeace.

## About

*Our motto is "Let's make the world better, not replace it?!"*

Letopeace is a new generation social network focused more on mixed reality technologies and privacy.

Our goals are
- to help people feel closer to friends and family in a fast-paced world where there is often no time for communication and meetings;
- to protect the privacy of users.

## Setup environment

### macOS 12+ (Homebrew):

0. Update Homebrew: `brew update` (recommended)
1. Install required software using Homebrew:
   ```
   brew install postgresql
   brew install --cask intellij-idea-ce
   ```
2. Install JDK 17
4. Start PostgreSQL cluster: `brew services start postgresql`
5. Connect to PostgreSQL via `psql`: `psql postgres`

### Debian 11 (bullseye):

Coming soon

### Common:

1. Create new database and user as you need (by default Letopeace will connect to `postgres` database with user `admin` and password `admin`)
2. Execute all SQL-scripts with `upgrade` prefix one by one up to the latest one from the project folder: `src/main/resources/db/migrations`
3. Open the project in IntelliJ IDEA and go to <kbd>Run</kbd> &rarr; <kbd>Edit configurations...</kbd>
4. Press <kbd>+</kbd> &rarr; <kbd>Application</kbd> and give a some name for the created configuration
5. Select your JDK in dropdown list, `server.main` module and `com.kleinstein.server.ApplicationKt` class
6. Specify the project root as a working directory
7. If your database URL diffs from a default one, specify it in section **environment variables** using `DB_URL` variable name
8. Press <kbd>OK</kbd>

## Launch

Run: <kbd>Run</kbd> &rarr; <kbd>Run '<CONFIG_NAME>'</kbd>  

Debug: <kbd>Run</kbd> &rarr; <kbd>Debug '<CONFIG_NAME>'</kbd>

## Project structure

- `.idea/` contains IntelliJ Idea project files;
- `gradle/` contains Gradle binaries;
- `src/` contains a source code and resources;
- `.gitignore`contains a list of rules to exclude files and folders which shouldn't be tracked by Git;
- `build.gradle` is a configuration file for [Gradle build system](https://gradle.org);
- `gradle.properties` consists of key-value pairs, an alternative variant to set Gradle build flags;
- `settings.gradle` is a root configuration file for [Gradle build system](https://gradle.org), allows building multiple projects;

## Useful resources

- [Kotlin](https://kotlinlang.org)
- [Ktor](https://ktor.io)
- [Exposed](https://github.com/JetBrains/Exposed)
- [PostgreSQL](https://www.postgresql.org)
- [RESTful](https://medium.com/@mwaysolutions/10-best-practices-for-better-restful-api-cbe81b06f291)

## Licensing
Project is licensed under the Apache License, Version 2.0. See
[LICENSE](https://github.com/Klein-Stein/letopeace-server/blob/master/LICENSE.txt) for the full
license text.
