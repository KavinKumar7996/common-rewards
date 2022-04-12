apply<EnterpriseRepositoryPlugin>()

class EnterpriseRepositoryPlugin : Plugin<Gradle> {

    override fun apply(gradle: Gradle) {
        val nexusUri: String = System.getenv("NEXUS_URL") ?: ""
        val repositoryUri = "$nexusUri/repository/maven-public/"
        val nexusUsername: String = System.getenv("NEXUS_USERNAME") ?: ""
        val nexusPassword: String = System.getenv("NEXUS_PASSWORD") ?: ""
        val useNexus = nexusUri.isNotBlank() && nexusUsername.isNotBlank() && nexusPassword.isNotBlank()
        if (useNexus) {
            gradle.settingsEvaluated {
                settings.pluginManagement {
                    repositories {
                        all {
                            if (this !is MavenArtifactRepository || url.toString() != repositoryUri) {
                                println(
                                    "Plugin repository ${(this as? MavenArtifactRepository)?.url ?: name} removed"
                                )
                                remove(this)
                            }
                        }
                        add(
                            maven(url = repositoryUri) {
                                credentials {
                                    username = nexusUsername
                                    password = nexusPassword
                                }
                            }
                        )
                    }
                }
            }
            gradle.allprojects {
                buildscript {
                    repositories {
                        all {
                            if (this !is MavenArtifactRepository || url.toString() != repositoryUri) {
                                println(
                                    "Build script repository ${(this as? MavenArtifactRepository)?.url ?: name} removed"
                                )
                                remove(this)
                            }
                        }
                        add(
                            maven(url = repositoryUri) {
                                credentials {
                                    username = nexusUsername
                                    password = nexusPassword
                                }
                            }
                        )
                    }
                }
                repositories {
                    all {
                        if (this !is MavenArtifactRepository || url.toString() != repositoryUri) {
                            project.logger.lifecycle(
                                "Repository ${(this as? MavenArtifactRepository)?.url ?: name} removed"
                            )
                            remove(this)
                        }
                    }
                    add(
                        maven(url = repositoryUri) {
                            credentials {
                                username = nexusUsername
                                password = nexusPassword
                            }
                        }
                    )
                }
            }
        }
    }
}
