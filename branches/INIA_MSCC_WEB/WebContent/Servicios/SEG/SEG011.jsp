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
						<h:panelGrid width="950" cellpadding="0" cellspacing="0">
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

									<rich:panel headerClass="tituloPantalla"
										style="background-color: #ebf3fd;">
										<f:facet name="header">
											<h:outputText value="Envio Solicitud de Permisos" />
										</f:facet>

										<h:panelGrid columns="2" rendered="#{loginBean.logged}"
											columnClasses="cols">
											<h:column>
												<h:panelGroup rendered="#{menuBean.init}" />
												<rich:panelMenu binding="#{menuBean.panelMenu}" />
											</h:column>
											<h:column>
												<h:panelGrid>
													<h:panelGrid columns="2" width="400px"
														columnClasses="textoPlano,textoDataTable">

														<h:outputText value="#{text.perfil_Nombre}" />
														<h:inputText label="Name" id="name" 
															style=" width : 245px;">
														</h:inputText>

														<h:outputText value="E-Mail" />
														<h:inputText label="Name" id="name"
															style=" width : 245px;">
														</h:inputText>

														<h:outputText value="#{text.perfil_Descripcion}" />
														<h:inputTextarea id="Detalle Solicitud"
															style=" width : 245px; height : 71px;" />

														<h:outputText value="Fecha Solicitud" />
														<rich:calendar id="calFecha"
															inputClass="rich-calendar-input"
															enableManualInput="false" locale="ES" disabled="true"
															showApplyButton="false" datePattern="dd/MM/yyyy"
															popup="true" cellWidth="24px" cellHeight="22px"
															style="width:245px" />

														<td style="width: 2px;"></td>
														<h:outputText value="" />

													</h:panelGrid>

													<center><h:panelGrid columns="3">
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2; width : 120px;"
															styleClass="textoPlano" action="#{}"
															value="Enviar Solicitud" />

														<a4j:commandButton immediate="true"
															style="font-size: 10pt; color: #2d77c2; width : 120px;"
															styleClass="textoPlano" action="salir"
															value="#{text.boton_Cancelar}" />
													</h:panelGrid></center>

													<center><f:facet name="footer">
														<h:panelGrid>
															<rich:messages styleClass="mensajeError">
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
																</f:facet>
															</rich:messages>
															<h:outputText styleClass="textoPlano"
																value="#{datosUsuarioBean.exito}" />
														</h:panelGrid>
													</f:facet></center>
												</h:panelGrid>
											</h:column>
										</h:panelGrid>
									</rich:panel>
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