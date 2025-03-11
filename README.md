# Project Documentation

## Overview
This project is a Spring Boot backend service with REST APIs, authentication, monitoring, and containerized infrastructure.

## Technology Stack
- **Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Databases**:
  - PostgreSQL (Development)
  - H2 (Production)
- **Documentation**: Swagger/OpenAPI
- **Monitoring**:
  - ELK Stack (Elasticsearch, Logstash, Kibana)
  - Prometheus and Grafana
- **Authentication**: JWT-based
- **Email**: SendGrid integration

## Environment Configurations

### Development Environment
- PostgreSQL database connection
- SendGrid email integration with verification
- Swagger UI enabled
- JWT configuration with access and refresh tokens
- SQL query logging enabled

### Production Environment
- H2 database with Hibernate dialect
- Swagger UI disabled
- Simpler JWT configuration

## Infrastructure (Docker Compose)

The application architecture includes:

- **Backend Service**:
  - Custom Docker image
  - Exposed on port 8080
  - Connects to PostgreSQL

- **Databases**:
  - PostgreSQL (port 5439)
  - MySQL (port 3307, alternative)

- **Logging & Monitoring**:
  - Elasticsearch (port 9200)
  - Kibana (port 5601)
  - Logstash (port 5600)
  - Prometheus (port 9090)
  - Grafana (port 3000)

## Prometheus Configuration
Prometheus is configured to scrape metrics from:
- Prometheus itself (`localhost:9090`)
- Backend service's actuator endpoint (`/actuator/prometheus`)

## Security Notes
- Different JWT keys for development and production
- SendGrid API keys should be secured properly
- Database credentials in configuration files should be protected

## Running the Application
The application and its infrastructure can be started using Docker Compose:
```bash
docker-compose up -d
```

Monitoring dashboards can be accessed at:
- **Kibana**: [http://localhost:5601](http://localhost:5601)
- **Grafana**: [http://localhost:3000](http://localhost:3000) (admin/password)
- **Prometheus**: [http://localhost:9090](http://localhost:9090)
