@echo off
REM Logi Project Build Script for Windows
REM Java 25, Kotlin 2.3.0, Gradle 9.2.1

setlocal

echo ============================================
echo Logi Logistics Ecosystem - Build Script
echo ============================================
echo.

REM Check Java version
echo [1/5] Checking Java version...
java -version
if errorlevel 1 (
    echo ERROR: Java is not installed or not found in PATH
    exit /b 1
)

REM Check Gradle version
echo [2/5] Checking Gradle version...
./gradlew --version
if errorlevel 1 (
    echo ERROR: Gradle wrapper failed to execute
    exit /b 1
)

REM Clean previous builds
echo [3/5] Cleaning previous builds...
./gradlew clean
if errorlevel 1 (
    echo ERROR: Clean failed
    exit /b 1
)

REM Build project
echo [4/5] Building project...
./gradlew build -x test
if errorlevel 1 (
    echo ERROR: Build failed
    exit /b 1
)

echo.
echo ============================================
echo Build completed successfully!
echo ============================================
echo.
echo To run the services:
echo   ./gradlew bootRun
echo.
echo To run infrastructure with Docker:
echo   docker-compose up -d
echo.
echo To view logs:
echo   docker-compose logs -f
echo.

endlocal
pause
