# Install bioface core

1. Install and configure required tools (mongodb, solr, keycloak)
2. Create infrastructure for bioface core - as `root` run script `install.sh`. This script create bioface user, basic directory structure, start script for bioface system and systemd service file for bioface system
3. Copy `application.properties.template` to `/opt/bioface/etc/application.properties`. Verify and optionally edit this file.
4. Copy application .jar file to `/opt/bioface/lib/bioface.jar`
5. Start application via service. As `root` run `systemctl start bioface`
6. Verify application status via `systemctl status bioface`
7. (Optionally) Set autostart for bioface service. As `root` run `systemct enable bioface`

# Upgrade bioface

1. Stop application via `systemctl stop bioface`
2. Copy new application .jar file to `/opt/bioface/lib/bioface.jar`
3. Verify if there is a need to edit `application.properties`. If so edit it.
4. Start application via `systemctl start bioface`
6. Verify application status via `systemctl status bioface`
