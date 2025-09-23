# Installation Guide

This guide will help you install and set up Reactor IDE on your system.

## Prerequisites

Before installing Reactor IDE, ensure you have the following:

- **Java Development Kit (JDK) 24 or higher**
- **Git** (for version control features)
- **Maven** (if building from source)

## Installation Methods

### Option 1: Pre-built JAR (Recommended)

1. Download the latest `reactor.jar` file from the releases section
2. Place the JAR file in your preferred directory
3. Run the application:
   ```bash
   java -jar reactor.jar
   ```

### Option 2: Build from Source

<procedure title="Building Reactor IDE from source" id="build-from-source">
    <step>
        <p>Clone the repository:</p>
        <code-block lang="bash">
            git clone https://github.com/theonetruenerd/reactor.git
            cd reactor
        </code-block>
    </step>
    <step>
        <p>Build the project using Maven:</p>
        <code-block lang="bash">
            ./mvnw clean compile
        </code-block>
    </step>
    <step>
        <p>Run the application:</p>
        <code-block lang="bash">
            ./mvnw javafx:run
        </code-block>
    </step>
</procedure>

## Verifying Installation

After launching Reactor IDE, you should see:
- The main application window
- Menu bar with File, Edit, View, Tools, and Help menus
- Project explorer panel
- Code editor area
- Git panel
- Console panel with Log, Output, and Terminal tabs

## Troubleshooting

### Common Issues

**Application won't start**
- Verify Java 24+ is installed: `java -version`
- Check that JavaFX modules are available
- Ensure sufficient memory is available

**Missing JavaFX modules**
- If running on Java 11+, JavaFX may need to be installed separately
- The bundled version includes all required JavaFX dependencies

**Performance Issues**
- Increase JVM memory: `java -Xmx4G -jar reactor.jar`
- Close unused projects to free up resources