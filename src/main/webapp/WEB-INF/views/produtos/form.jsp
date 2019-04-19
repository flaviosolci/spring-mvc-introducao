<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Casa do código</title>
</head>
<body>

	<form:form action="${s:mvcUrl('PC#gravar').build() }" method="post"
		modelAttribute="produto" enctype="multipart/form-data">
		<div>
			<label for="titulo">Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>

		<div>
			<label for="descricao">Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao" />
			<form:errors path="descricao" />
		</div>

		<div>
			<label for="paginas">Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />
		</div>

		<div>
			<label for="dataLancamento">Data de Lançamento</label>
			<form:input path="dataLancamento" />
			<form:errors path="dataLancamento" />
		</div>


		<c:forEach items="${ tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor" />
				<form:hidden path="precos[${status.index}].tipo"
					value="${tipoPreco}" />
			</div>
		</c:forEach>
		<div>
			<label for="sumario">Sumário</label> <input type="file"
				name="sumario" />
		</div>
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>