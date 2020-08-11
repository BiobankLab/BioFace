var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  SERVER_URL: '"http://localhost:8082/"',
  KEYCLOAK_REALM: '"bioface"',
  KEYCLOAK_CLIENT: '"bioface-client-ux"',
  KEYCLOAK_AUTH_URL: '"http://localhost:8081/auth"'
})
