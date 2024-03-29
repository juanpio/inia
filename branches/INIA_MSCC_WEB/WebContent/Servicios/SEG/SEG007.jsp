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
<script src="<%=request.getContextPath()%>/Recursos/Scripts/JSComun.js"
	type="text/javascript" language="javascript"></script>
</head>
<body>
<f:loadBundle basename="com.bean.text" var="text" />
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
					<td align="center" valign="top" class="contenido"><a4j:region
						id="contenido">
						<h:panelGroup rendered="#{loginBean.init}" />
						<h:panelGrid width="956" cellpadding="0" cellspacing="0">
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd;">
								<f:facet name="header">
									<h:panelGrid columns="2" width="900px">
										<h:column>
											<h:outputText
												style="font-size: 9pt; color: #2d77c2; width: 750; aling: left"
												value="Usuario #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}  -  Ultimo acceso #{loginBean.fechaEjecucionFormt}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:commandLink
												style="font-size: 8pt; color: #2d77c2; width: 100; aling: right"
												styleClass="textoPlano" action="#{loginBean.logout}"
												immediate="true" value="Cerrar Sesión">
											</h:commandLink>
										</h:column>
									</h:panelGrid>
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:panelGrid>
										<center><h:outputText styleClass="mensajeError"
											style="font-size: 12pt" value="#{text.login_notLogged}" /></center>

										<center><a4j:commandButton
											style="font-size: 10pt; color: #2d77c2; width : 120px;"
											styleClass="textoPlano" action="#{loginBean.logout}"
											value="#{text.login_Login}" /></center>
									</h:panelGrid>
								</h:panelGrid></center>
								<h:panelGroup rendered="#{loginBean.logged}">
									<h:panelGrid columns="2" rendered="#{loginBean.logged}"
										columnClasses="cols" width="200">
										<h:column>
											<h:panelGroup rendered="#{menuBean.init}" />
											<rich:panelMenu binding="#{menuBean.panelMenu}" />
										</h:column>
										<h:column>
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.registro_CambiarPass}" />
													</f:facet>
													<h:panelGrid>
														<h:panelGroup>

															<h:panelGrid columns="2" width="480"
																columnClasses="textoPlano,textoPlano">
																<h:outputText value="#{text.reenvioPassword_Actual}"
																	styleClass="textoPlano" />
																<h:inputSecret id="txtAcual"
																	value="#{recuperarContraseniaBean.actual}"
																	label="Debe ingresar su contraseña actual"
																	styleClass="textoPlano" tabindex="1" required="true"
																	onkeypress="ValidarCampoLogin(this,event)"
																	maxlength="13"
																	requiredMessage="Debe ingresar frase secreta">
																	<f:validateLength minimum="6" maximum="13" />
																	<a4j:support
																		action="#{recuperarContraseniaBean.validarContrasenia}"
																		event="onblur" ajaxSingle="true" />
																</h:inputSecret>

																<h:outputText value="#{text.registro_Contrasenia}"
																	styleClass="textoPlano" />
																<h:inputSecret id="txtContrasenia"
																	label="Debe ingresar Contraseña"
																	value="#{recuperarContraseniaBean.contrasenia}"
																	required="true"
																	requiredMessage="Debe ingresar la contraseña nueva"
																	onkeypress="ValidarCampoLogin(this, event)"
																	styleClass="textoPlano" tabindex="2" maxlength="13">
																	<f:validateLength minimum="6" maximum="13" />
																</h:inputSecret>

																<h:outputText
																	value="#{text.registro_ConfirmacionContrasenia}"
																	styleClass="textoPlano" />
																<h:inputSecret id="txtConfirmarContrasenia"
																	label="Debe ingresar confirmación de contraseña"
																	value="#{recuperarContraseniaBean.confirmacion}"
																	required="true"
																	onkeypress="ValidarCampoLogin(this, event)"
																	requiredMessage="Debe ingresar confirmación de contraseña"
																	styleClass="textoPlano" tabindex="3" maxlength="13">
																	<f:validateLength minimum="6" maximum="13" />
																	<a4j:support
																		action="#{recuperarContraseniaBean.confirmacionIngreso}"
																		event="onblur" />
																</h:inputSecret>


															</h:panelGrid>
															<center><h:panelGrid columns="2"
																columnClasses="textoPlano,textoPlano">
																<a4j:commandButton
																	style="font-size: 10pt; color: #2d77c2; width : 120px;"
																	styleClass="textoPlano" tabindex="4"
																	action="#{recuperarContraseniaBean.confirmar}"
																	value="#{text.boton_Guardar}" />
																<a4j:commandButton
																	style="font-size: 10pt; color: #2d77c2; width : 120px;"
																	styleClass="textoPlano" tabindex="5" action="cancelar"
																	immediate="true" value="#{text.boton_Cancelar}" />
															</h:panelGrid></center>
														</h:panelGroup>


													</h:panelGrid>
												</rich:panel>
												<center><f:facet name="footer">
													<h:panelGrid>
														<rich:messages styleClass="mensajeError">
															<f:facet name="errorMarker">
																<h:graphicImage
																	value="/Recursos/Imagenes/Iconos/error.gif" />
															</f:facet>
														</rich:messages>
														<h:outputText styleClass="textoPlano"
															value="#{recuperarContraseniaBean.exito}" />
													</h:panelGrid>
												</f:facet></center>

											</h:panelGrid>
										</h:column>
									</h:panelGrid>
								</h:panelGroup>
							</rich:panel>
						</h:panelGrid>
						<a4j:status for="contenido"
							onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:100, top:200,height:'90px'})"
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