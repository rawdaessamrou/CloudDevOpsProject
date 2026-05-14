variable "region"        { default = "us-east-1" }
variable "key_name"      { description = "EC2 key pair name" }
variable "ecr_repo_name" { default = "clouddevops-app" }
variable "cluster_name"  { default = "clouddevops-cluster" }