### Hexlet tests and linter status:
[![Actions Status](https://github.com/Grad566/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Grad566/java-project-72/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/8f1d363e2bfa27c9e1e1/maintainability)](https://codeclimate.com/github/Grad566/java-project-72/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/8f1d363e2bfa27c9e1e1/test_coverage)](https://codeclimate.com/github/Grad566/java-project-72/test_coverage)
[![My test](https://github.com/Grad566/Page-Analyzer/actions/workflows/myTest.yml/badge.svg?event=push)](https://github.com/Grad566/Page-Analyzer/actions/workflows/myTest.yml)

## Описание
Page-Analyzer - cайт, который анализирует указанные страницы на SEO пригодность.

Развернутое приложение: https://java-project-72-kxn1.onrender.com/

## Локальный запуск
Требования:
 - jdk 20
 - gradle 8.6

Запуск приложения
```
make dev
```

После чего приложение будет доступно по http://localhost:7070/

В качестве бд будет H2. (на продакшене PostgreSQL)

Дополнительные команды:
```
// запуск checkStyle
make lint 

// запуск тестов
make test 

// сборка приложения
make build 
```
