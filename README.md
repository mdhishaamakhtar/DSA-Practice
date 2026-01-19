# DSA-Practice

This project contains Java solutions for various Data Structures and Algorithms problems.

## Code Formatting

We use the [Spotify fmt-maven-plugin](https://github.com/spotify/fmt-maven-plugin) which follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

### Format the Whole Project

To format all Java files in the project, run:

```bash
mvn fmt:format
```

### Format a Specific File

To format a specific file or files matching a pattern, use the `filesNamePattern` property:

```bash
mvn fmt:format -Dfmt.filesNamePattern=.*YourFileName\.java
```

### Check Formatting

To check if the files are formatted correctly without applying changes:

```bash
mvn fmt:check
```
