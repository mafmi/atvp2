-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mecanicaMB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mecanicaMB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mecanicaMB` DEFAULT CHARACTER SET utf8 ;
USE `mecanicaMB` ;

-- -----------------------------------------------------
-- Table `mecanicaMB`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mecanicaMB`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `cpf_id` VARCHAR(45) NULL,
  `nome_cli` VARCHAR(45) NULL,
  `end_cli` VARCHAR(45) NULL,
  `telefone` VARCHAR(10) NULL,
  `email_cli` VARCHAR(45) NULL,
  PRIMARY KEY (`idcliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mecanicaMB`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mecanicaMB`.`veiculo` (
  `idveiculo` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(45) NULL,
  `marca_ve` VARCHAR(45) NULL,
  `ano_ve` INT NULL,
  `cliente_idcliente` INT NOT NULL,
  PRIMARY KEY (`idveiculo`),
  INDEX `fk_veiculo_pesado_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_veiculo_pesado_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `mecanicaMB`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mecanicaMB`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mecanicaMB`.`servico` (
  `idservico` INT NOT NULL AUTO_INCREMENT,
  `dt_entrada` DATE NULL,
  `dt_saida` DATE NULL,
  `valor_ser` VARCHAR(45) NULL,
  `veiculo_idveiculo` INT NOT NULL,
  PRIMARY KEY (`idservico`),
  INDEX `fk_servico_veiculo1_idx` (`veiculo_idveiculo` ASC) VISIBLE,
  CONSTRAINT `fk_servico_veiculo1`
    FOREIGN KEY (`veiculo_idveiculo`)
    REFERENCES `mecanicaMB`.`veiculo` (`idveiculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mecanicaMB`.`peca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mecanicaMB`.`peca` (
  `idpeca` INT NOT NULL AUTO_INCREMENT,
  `nome_pec` VARCHAR(45) NULL,
  `tipo_pec` VARCHAR(45) NULL,
  PRIMARY KEY (`idpeca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mecanicaMB`.`peca_has_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mecanicaMB`.`peca_has_servico` (
  `peca_idpeca` INT NOT NULL,
  `servico_idservico` INT NOT NULL,
  PRIMARY KEY (`peca_idpeca`, `servico_idservico`),
  INDEX `fk_peca_has_servico_servico1_idx` (`servico_idservico` ASC) VISIBLE,
  INDEX `fk_peca_has_servico_peca1_idx` (`peca_idpeca` ASC) VISIBLE,
  CONSTRAINT `fk_peca_has_servico_peca1`
    FOREIGN KEY (`peca_idpeca`)
    REFERENCES `mecanicaMB`.`peca` (`idpeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_peca_has_servico_servico1`
    FOREIGN KEY (`servico_idservico`)
    REFERENCES `mecanicaMB`.`servico` (`idservico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
