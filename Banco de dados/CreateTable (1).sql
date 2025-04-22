
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agencia_viagens` DEFAULT CHARACTER SET bd_agencia;
USE `agencia_viagens` ;

-- -----------------------------------------------------
-- Table `agencia_viagens`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`Clientes` (
  `idClientes` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `passaporte` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nacionalidade` ENUM('NACIONAL', 'ESTRANGEIRO') NOT NULL,
  `data_cadastro` DATE NOT NULL,
  CONSTRAINT chk_documento CHECK (
        (tipo = 'NACIONAL' AND cpf IS NOT NULL AND passaporte IS NULL) OR
        (tipo = 'ESTRANGEIRO' AND passaporte IS NOT NULL AND cpf IS NULL)
    ),
  PRIMARY KEY (`idClientes`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `passaporte_UNIQUE` (`passaporte` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agencia_viagens`.`PacoteViagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`PacoteViagem` (
  `idPacoteViagem` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `destino` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  `duracao_dias` INT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `tipo` ENUM('Nacional', 'Internacional') NOT NULL,
  CONSTRAINT chk_preco_positivo CHECK (preco > 0),
  PRIMARY KEY (`idPacoteViagem`),
  PRIMARY KEY (`idPacoteViagem`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agencia_viagens`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `data_pedido` DATE NOT NULL,
  `valor_total` DECIMAL NOT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `IdCliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `IdCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `agencia_viagens`.`Clientes` (`idClientes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agencia_viagens`.`ServicoAdicional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`ServicoAdicional` (
  `idServicoAdicional` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  CONSTRAINT chk_preco_servico_positivo CHECK (preco > 0),
  PRIMARY KEY (`idServicoAdicional`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agencia_viagens`.`PedidoPacote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`PedidoPacote` (
  `Pedido_idPedido` INT NOT NULL,
  `PacoteViagem_idPacoteViagem` INT NOT NULL,
  PRIMARY KEY (`Pedido_idPedido`, `PacoteViagem_idPacoteViagem`),
  INDEX `fk_Pedido_has_PacoteViagem_PacoteViagem1_idx` (`PacoteViagem_idPacoteViagem` ASC) VISIBLE,
  INDEX `fk_Pedido_has_PacoteViagem_Pedido1_idx` (`Pedido_idPedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_PacoteViagem_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `agencia_viagens`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_PacoteViagem_PacoteViagem1`
    FOREIGN KEY (`PacoteViagem_idPacoteViagem`)
    REFERENCES `agencia_viagens`.`PacoteViagem` (`idPacoteViagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agencia_viagens`.`PedidoServico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agencia_viagens`.`PedidoServico` (
  `Pedido_idPedido` INT NOT NULL,
  `ServicoAdicional_idServicoAdicional` INT NOT NULL,
  `quantidade` INT NOT NULL DEFAULT 1,
  `preco_unitario` DECIMAL(10,2) NOT NULL,
  CONSTRAINT chk_quantidade_positiva CHECK (quantidade > 0),
  CONSTRAINT chk_preco_unitario_positivo CHECK (preco_unitario > 0),
  PRIMARY KEY (`Pedido_idPedido`, `ServicoAdicional_idServicoAdicional`),
  INDEX `fk_Pedido_has_ServicoAdicional_ServicoAdicional1_idx` (`ServicoAdicional_idServicoAdicional` ASC) VISIBLE,
  INDEX `fk_Pedido_has_ServicoAdicional_Pedido1_idx` (`Pedido_idPedido` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_has_ServicoAdicional_Pedido1`
    FOREIGN KEY (`Pedido_idPedido`)
    REFERENCES `agencia_viagens`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_ServicoAdicional_ServicoAdicional1`
    FOREIGN KEY (`ServicoAdicional_idServicoAdicional`)
    REFERENCES `agencia_viagens`.`ServicoAdicional` (`idServicoAdicional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
