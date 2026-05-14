terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.region
}

module "network" {
  source = "./modules/network"
  region = var.region
}

module "server" {
  source            = "./modules/server"
  vpc_id            = module.network.vpc_id
  public_subnet_id  = module.network.public_subnet_ids[0]
  key_name          = var.key_name
}

module "ecr" {
  source = "./modules/ecr"
  repo_name = var.ecr_repo_name
}

module "eks" {
  source              = "./modules/eks"
  vpc_id              = module.network.vpc_id
  private_subnet_ids  = module.network.private_subnet_ids
  cluster_name        = var.cluster_name
}