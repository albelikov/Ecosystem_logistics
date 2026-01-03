# Анализ проблем проекта Logi и решения

## Проблемы совместимости версий

### Версии компонентов

| Компонент | Требуемая версия | Проверка |
|-----------|-----------------|----------|
| Java | 25+ | `java -version` |
| Kotlin | 2.3.0 | `kotlin -version` |
| Gradle | 9.2.1 | `./gradlew --version` |
| Spring Boot | 3.4.0 | build.gradle.kts |
| PostgreSQL | 16+ | `psql --version` |
| Redis | 7+ | `redis-server --version` |

### Решения для распространённых проблем

#### Проблема: Kotlin версия не совпадает

**Симптомы:**
- `Kotlin IllegalArgumentException` при компиляции
- Несовместимость с плагинами

**Решение:**
```bash
# Обновить версию в gradle.properties
kotlin.version=2.3.0

# Очистить кеш
./gradlew clean
rm -rf ~/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin
```

#### Проблема: Gradle версия несовместима

**Симптомы:**
- `UnsupportedClassVersionError`
- Ошибки при загрузке плагинов

**Решение:**
```bash
# Обновить wrapper
./gradlew wrapper --gradle-version=9.2.1

# Или вручную обновить gradle/wrapper/gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-9.2.1-bin.zip
```

#### Проблема: Spring Boot версия

**Симптомы:**
- `BeanCreationException`
- Missing dependencies

**Решение:**
```kotlin
// В build.gradle.kts
plugins {
    id("org.springframework.boot") version "3.4.0" apply false
}

// В каждом модуле
plugins {
    id("org.springframework.boot")
}
```

## Проблемы с памятью

### OutOfMemoryError: Metaspace

**Симптомы:**
- `java.lang.OutOfMemoryError: Metaspace`
- Gradle daemon crash

**Решение в gradle.properties:**
```properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError
```

### OutOfMemoryError: GC overhead limit

**Симптомы:**
- `java.lang.OutOfMemoryError: GC overhead limit exceeded`

**Решение:**
```properties
org.gradle.jvmargs=-Xmx4g -XX:+UseG1GC -XX:MaxGCPauseMillis=200
```

## Проблемы с базой данных

### PostgreSQL соединение

**Симптомы:**
- `Connection refused`
- `FATAL: database "logi" does not exist`

**Решение:**
```bash
# Создать базу данных
psql -U postgres -c "CREATE DATABASE logi;"

# Или использовать Docker
docker-compose up -d postgres-primary
```

### Миграции Flyway

**Симптомы:**
- `FlywayException: Validate failed`
- Schema version mismatch

**Решение:**
```bash
# Очистить и пересоздать схему (только для разработки!)
./gradlew flywayClean
./gradlew flywayMigrate

# Или baseline
./gradlew flywayBaseline
```

## Проблемы с Redis

### Подключение

**Симптомы:**
- `RedisConnectionException`
- Unable to connect to localhost:6379

**Решение:**
```bash
# Запуск Redis
docker-compose up -d redis

# Или локально
redis-server --daemonize yes
```

### Кеш

**Симптомы:**
- Старые данные в кеше
- Несоответствие данных

**Решение:**
```bash
# Очистка Redis
redis-cli FLUSHALL
```

## Проблемы с Docker

### Порт уже используется

**Симптомы:**
- `Bind for 0.0.0.0:8080 failed: port is already allocated`

**Решение:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux
lsof -i :8080
kill -9 <PID>
```

### Недостаточно памяти

**Симптомы:**
- Docker container exited with code 137
- OOM killer

**Решение:**
```yaml
# docker-compose.yml
services:
  postgres:
    deploy:
      resources:
        limits:
          memory: 4G
        reservations:
          memory: 2G
```

## Проблемы с модулями

### Circular dependency

**Симптомы:**
- `BeanCurrentlyInCreationException`
- Circular reference

**Решение:**
```kotlin
// Использовать @Lazy
@Autowired
@Lazy
private lateinit var someService: SomeService

// Или рефакторинг архитектуры
// Выделить общий модуль
```

### Module not found

**Симптомы:**
- `Project with path ':scm-iam' could not be found`
- Build errors

**Решение:**
```kotlin
// В settings.gradle.kts
include(
    "admin-panel",
    "scm-iam",
    "scm-audit",
    // ... все модули
)
```

## Проблемы с безопасностью

### Keycloak

**Симптомы:**
- `401 Unauthorized`
- Token validation failed

**Решение:**
```yaml
# application.yml
keycloak:
  auth-server-url: http://localhost:8180
  realm: logi
  resource: logi-iam
```

## Чек-лист перед запуском

- [ ] Java 25 установлена и `JAVA_HOME` настроена
- [ ] Gradle wrapper работает (`./gradlew --version`)
- [ ] PostgreSQL запущена и база создана
- [ ] Redis запущен
- [ ] Keycloak запущен и realm настроен
- [ ] Docker запущен
- [ ] Ports свободны (5432, 6379, 8080-8088, 8180)
- [ ] `.env` файл создан с необходимыми переменными

## Полезные команды

```bash
# Полная очистка и пересборка
./gradlew clean
rm -rf ~/.gradle/caches
./gradlew build -x test

# Запуск с профилем
./gradlew bootRun --args='--spring.profiles.active=dev'

# Просмотр зависимостей
./gradlew dependencies

# Диагностика
./gradlew build --info --stacktrace > build.log
```

## Контакты для поддержки

- Создайте issue на GitHub
- Проверьте логи в `build_log.txt`
- Используйте `build_script.bat` для автоматической диагностики
