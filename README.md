# Classifika API

**Classifika** é uma aplicação de classificados Web similar ao "olx", nela você pode anunciar produtos, serviços, empregos, etc... Você pode pesquisar um anúncio facilmente, com filtros de categoria e estado.
Com uma interface elegante, construída com tecnologia inovadora que te possibilita fazer uploads de fotos para você conseguir demonstrar o seu produto em alta definição.

## Live Demo

Link : https://classifika-ui.herokuapp.com/

## Front-end Repository

Link: https://github.com/emanuelhonorio/classifika-ui

## Project Structure

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂emanuelhonorio
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BasicAuthConfig.java
 ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailAlreadyUsedException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FotoStorageException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResourceNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResourceOwnerException.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ExceptionHandlerGlobal.java
 ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┗ 📜CorsFilter.java
 ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┣ 📂enums
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜EstadoUF.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Anuncio.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Categoria.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Contato.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Foto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Role.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Usuario.java
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AnuncioRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoriaRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ContatoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FotoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioRepository.java
 ┃ ┃ ┃ ┃ ┣ 📂resource
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AccountResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AnuncioResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoriaResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜IndexResource.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MeResource.java
 ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDetailsServiceImpl.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AnuncioDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnuncioFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AnuncioMapper.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AnuncioService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FotoStorageService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioService.java
 ┃ ┃ ┃ ┃ ┗ 📜ClassifikaApplication.java
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂db
 ┃ ┃ ┃ ┗ 📂migration
 ┃ ┃ ┃ ┃ ┣ 📜V01__base.sql
 ┃ ┃ ┃ ┃ ┣ 📜V02__add_expiracao_do_anuncio.sql
 ┃ ┃ ┃ ┃ ┣ 📜V03__add_contato_e_relacionamentos.sql
 ┃ ┃ ┃ ┃ ┣ 📜V04__salvando_foto_no_banco.sql
 ┃ ┃ ┃ ┃ ┣ 📜V05__add__admin_role.sql
 ┃ ┃ ┃ ┃ ┗ 📜V06__modify_anuncio_foto_to_mediumblob.sql
 ┃ ┃ ┣ 📜application-prod.properties
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂emanuelhonorio
 ┃ ┃ ┃ ┃ ┗ 📜ClassifikaApplicationTests.java
```
