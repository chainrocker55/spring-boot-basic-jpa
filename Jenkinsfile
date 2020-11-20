@Library("devops-shared-library@DevMaster") _


def GIT_CREDENTIALS_ID = "gituser"
def JFROG_CREDENTIALS_ID = "artifactoryuser"
def DOCKER_REPO = "academy-docker"
def APP_MODULE = "basic-java-nov-2020"
def SOURCE_BRANCH = env.gitlabBranch ?: '965530-CICD' // change to {EMPLOYEE_ID}-CICD

def params = [
        containers: [
                [name: 'oc-cli', tag: '3.6.0'],
                [name: 'kaniko'],
        ]
]
kslave params, {
    node(POD_LABEL) {
        def kscmParams = [
                repo   : [
                        projectId: "Academy/${APP_MODULE}",
                        branch   : "${SOURCE_BRANCH}",
                ], auth: [credentialsId: GIT_CREDENTIALS_ID]
        ]
        stage('PULL-CODE') {
            kscmCheckout(kscmParams) {
                GIT_COMMIT = it
                currentBuild.displayName = "#${env.BUILD_NUMBER}_${it}"
            }
        }

        def targetImage = "${DOCKER_REPO}.artifactory.kasikornbank.com:8443/dev/${APP_MODULE}:${GIT_COMMIT}"
        stage('BUILD-DOCKER-IMAGE') {
            withMavenSetting([
                    auth  : [credentialsId: JFROG_CREDENTIALS_ID],
                    config: [
                            appId: "academy"
                    ]
            ]) { xml ->
                writeFile file: "${WORKSPACE}/settings.xml", text: xml
            }
            def kanikoParams = [
                    auth  : [
                            [registry: "docker.artifactory.kasikornbank.com:8443", credentialsId: JFROG_CREDENTIALS_ID],
                            [registry: "common-docker.artifactory.kasikornbank.com:8443", credentialsId: JFROG_CREDENTIALS_ID],
                            [registry: "${DOCKER_REPO}.artifactory.kasikornbank.com:8443", credentialsId: JFROG_CREDENTIALS_ID],
                    ],
                    params: [
                            cacheRepo   : "${DOCKER_REPO}.artifactory.kasikornbank.com:8443/cache",
                            context     : "dir://${WORKSPACE}",
                            destinations: [targetImage],
//                            dockerfile: "Dockerfile-build"
                    ]
            ]
            container('kaniko') {
                println '--- use kaniko with parameters ---'
//                writeFile file: "Dockerfile-build", text: '''
//                FROM docker.artifactory.kasikornbank.com:8443/maven:3.6.3-jdk-11-openj9 as builder as builder
//
//                USER root
//                ARG http_proxy
//                ARG https_proxy
//                ARG no_proxy
//
//                ENV http_proxy=http://172.31.97.120:8080
//                ENV https_proxy=http://172.31.97.120:8080
//                ENV no_proxy=$no_proxy
//
//                WORKDIR /src
//                COPY pom.xml .
//                COPY settings.xml .
//
//                RUN mvn -s settings.xml dependency:go-offline -B -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
//
//                WORKDIR /
//                COPY pom.xml /pom.xml
//                COPY src/main /src/main
//                COPY src/test /src/test
//                COPY settings.xml /settings.xml
//
//                RUN mvn clean install -s settings.xml -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
//
//                # Dockerfile
//                FROM docker.artifactory.kasikornbank.com:8443/openjdk:15-jdk
//                COPY --from=builder target/restservice-0.0.1-SNAPSHOT.jar .
//                CMD ["java", "-jar", "restservice-0.0.1-SNAPSHOT.jar"]
//                '''.stripIndent()
//
//                sh '''
//                    cat Dockerfile
//                    cat pom.xml
//                '''

                prettyPrintMap kanikoParams
                kanikoBuild(kanikoParams)
            }
        }

        def deploymentFile = 'k8s-deployment.yaml'
        stage('DEPLOY OPENSHIFT') {
            sh """
                ls -la
                cd k8s/base
                kustomize edit set image ${APP_MODULE}-image=${targetImage}
                kustomize build > ${WORKSPACE}/${deploymentFile}
            """
            withCredentials([string(credentialsId: "ocp35-token-academy", variable: 'OCP_TOKEN')]) {
                container("oc-cli") {
                    sh """
                        oc login https://openshift-test.kbankpocnet.com:443 --insecure-skip-tls-verify --token ${OCP_TOKEN}
                        cat ${WORKSPACE}/${deploymentFile}
                        oc apply -f ${WORKSPACE}/${deploymentFile}

                        cat k8s/base/route-config.yaml
                        oc apply -f k8s/base/route-config.yaml

                        cat k8s/base/service-config.yaml
                        oc apply -f k8s/base/service-config.yaml
                        oc rollout status dc/basic-java-nov-2020
                    """
                }
            }
        }
    }
}