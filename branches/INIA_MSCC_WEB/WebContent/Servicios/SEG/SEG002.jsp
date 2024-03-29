<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>INIA - Modelo de Simulación de Crecimiento de Cultivos de
Secano.</title>
<link
	href="<%=request.getContextPath()%>/Recursos/css/inia2010-01-17.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/Recursos/Scripts/JSComun.js"></script>
</head>
<body>
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<table align="center" width="956px">
			<thead>
				<tr>
					<td>
					<div class="logo"></div>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center" class="contenido"><a4j:region
						id="contenido">
						<h:panelGroup rendered="#{loginBean.init}" />
						<h:panelGrid>
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd; ">
								<f:facet name="header">
									<h:outputText value="#{text.login_NuevoUsuario}" />
								</f:facet>
								<h:panelGrid>
									<center><h:panelGrid rendered="#{loginBean.logged}">
										<h:outputText styleClass="mensajeError"
											value="#{loginBean.loginName} #{text.login_YaEstaLogueado}" />
										<center><a4j:commandButton
											style="font-size: 10pt; color: #2d77c2; width : 120px;"
											styleClass="textoPlano" action="#{loginBean.logout}"
											immediate="true" value="#{text.login_LogOut}" /></center>
									</h:panelGrid></center>

									<h:panelGroup rendered="#{!loginBean.logged}">
										<h:panelGrid columns="2" columnClasses="textoPlano,textoPlano">

											<h:outputText value="#{text.registro_Fecha}" />
											<rich:calendar id="calFechaEjecucion"
												inputClass="rich-calendar-input"
												value="#{registroBean.fecha}" enableManualInput="false"
												locale="ES" disabled="true" showApplyButton="false"
												datePattern="dd/MM/yyyy" popup="true" cellWidth="24px"
												cellHeight="22px" style="width:200px" />

											<h:outputText value="#{text.registro_Nombre}" />
											<h:inputText value="#{registroBean.nombre}" required="true"
												requiredMessage="Debe ingresar su nombre"
												styleClass="textoPlano"
												onkeypress="ValidarCampoLetras(this, event)" />

											<h:outputText value="#{text.registro_Apellido}" />
											<h:inputText value="#{registroBean.apellido}" required="true"
												requiredMessage="Debe ingresar su apellido"
												styleClass="textoPlano"
												onkeypress="ValidarCampoLetras(this, event)" />

											<h:outputText value="#{text.registro_Email}" />
											<h:inputText id="txtEmail" value="#{registroBean.email}"
												onblur="validarEmailBlur(this, event)" required="true"
												requiredMessage="Debe ingresar su e-mail de contacto"
												onkeypress="validarEmailKeyPress(this, event)"
												styleClass="textoPlano">
												<a4j:support action="#{registroBean.takeSelectionEmail}"
													event="onchange" ajaxSingle="true" reRender="txtEmail" />
											</h:inputText>

											<h:outputLabel value="#{text.registro_Pais}" />
											<rich:comboBox value="#{registroBean.paisElegido}"
												enableManualInput="false" styleClass="combo">
												<f:selectItems value="#{registroBean.paises}" />
												<a4j:support action="#{registroBean.takeSelectionPais}"
													event="onchange" ajaxSingle="true"
													reRender="cmbDepartamentos" />
											</rich:comboBox>

											<h:outputText
												value="#{text.registro_Departamento_Estado_Provincia}" />
											<rich:comboBox id="cmbDepartamentos"
												enableManualInput="false"
												value="#{registroBean.departamentoElegido}">
												<f:selectItems value="#{registroBean.departamentos}" />
												<a4j:support
													action="#{registroBean.takeSelectionDepartamento}"
													event="onchange" ajaxSingle="true" reRender="cmbCiudad" />
											</rich:comboBox>

											<h:outputText value="#{text.registro_Ciudad}" />
											<rich:comboBox id="cmbCiudad"
												value="#{registroBean.ciudadElegido}"
												enableManualInput="false">
												<f:selectItems value="#{registroBean.ciudades}" />
												<a4j:support action="#{registroBean.takeSelectionCiudad}"
													event="onchange" ajaxSingle="true" />
											</rich:comboBox>

											<h:outputText value="#{text.registro_Direccion}" />
											<h:inputText value="#{registroBean.direccion}"
												required="true" styleClass="textoPlano"
												requiredMessage="Debe ingresar su dirección de domicilio"
												onkeypress="ValidarCampoConCaracteresEspeciales(this, event)" />

											<h:outputText value="#{text.registro_Telefono}" />
											<h:inputText value="#{registroBean.telefono}"
												styleClass="textoPlano"
												onkeypress="ValidarCampoNumerico(this, event)" />

											<h:outputText value="#{text.registro_Celular}" />
											<h:inputText value="#{registroBean.celular}"
												styleClass="textoPlano"
												onkeypress="ValidarCampoNumerico(this, event)" maxlength="9" />
										</h:panelGrid>
										<br></br>
										<center><h:panelGrid columns="2">
											<a4j:commandButton style="font-size: 10pt; color: #2d77c2; width : 120px;"
												styleClass="textoPlano" tabindex="3"
												action="#{registroBean.registrar}"
												value="#{text.boton_Guardar}" />
											<a4j:commandButton style="font-size: 10pt; color: #2d77c2; width : 120px;"
												styleClass="textoPlano" action="cancelar" immediate="true"
												value="#{text.boton_Cancelar}" />
										</h:panelGrid></center>
									</h:panelGroup>
									<center><f:facet name="footer">
										<h:panelGrid>
											<rich:messages styleClass="mensajeError">
												<f:facet name="errorMarker">
													<h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />
												</f:facet>
											</rich:messages>
											<h:outputText styleClass="textoPlano"
												value="#{loginBean.exito}" />
										</h:panelGrid>
									</f:facet></center>
								</h:panelGrid>
							</rich:panel>
						</h:panelGrid>
						<a4j:status for="contenido"
							onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:'200px', top:200,height:'100px'})"
							onstop="Richfaces.hideModalPanel('ajaxLoadingModalBox')"></a4j:status>
					</a4j:region> <rich:modalPanel id="ajaxLoadingModalBox" minHeight="100"
						minWidth="200" height="40" width="400" zindex="100" styleClass="">
						<rich:panel style="background-color: #ebf3fd; ">
							<center>
							<h2><h:outputText value="Procesando..."
								styleClass="textoPlano">
							</h:outputText></h2>
							</center>
						</rich:panel>
					</rich:modalPanel></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
					<div class="copyrigth"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body>
</html>