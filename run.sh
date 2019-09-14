#!/usr/bin/env bash
cd ../service-registry
mvn spring-boot:run  > /dev/null 2>&1 &
cd ../productcatalog
pushd catalog-service
mvn spring-boot:run  > /dev/null 2>&1 &
popd && pushd catalog-bff
mvn spring-boot:run  > /dev/null 2>&1 &
popd && pushd catalog-web
mvn spring-boot:run  > /dev/null 2>&1 &
echo 'Done now ...'
