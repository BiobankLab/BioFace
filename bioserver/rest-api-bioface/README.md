# Build project with

gradle build

# (NOT RECOMMENDED) you can skip integration test by

gradle build -x integrationTest

# Before running server make sure that solr and mongo are running, then:

gradle bootRun

# or you can simply run class (as Java Application)

com.bioface.invoker.ApplicationRunner
