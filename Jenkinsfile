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
            def kanikoParams = [
                    auth  : [
                            [registry: "docker.artifactory.kasikornbank.com:8443", credentialsId: JFROG_CREDENTIALS_ID],
                            [registry: "${DOCKER_REPO}.artifactory.kasikornbank.com:8443", credentialsId: JFROG_CREDENTIALS_ID],
                    ],
                    params: [
                            cacheRepo   : "${DOCKER_REPO}.artifactory.kasikornbank.com:8443/cache",
                            context     : "dir://${WORKSPACE}",
                            destinations: [targetImage],
                    ]
            ]
            container('kaniko') {
                println '--- use kaniko with parameters ---'
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