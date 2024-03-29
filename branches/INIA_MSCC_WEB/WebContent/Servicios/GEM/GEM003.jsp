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
									<h:panelGrid columns="2" rendered="#{loginBean.logged}"
										columnClasses="cols" width="200">
										<h:column>
											<h:panelGroup rendered="#{menuBean.init}" />
											<rich:panelMenu binding="#{menuBean.panelMenu}" />
										</h:column>
										<h:column>


											<h:panelGroup rendered="#{propiedadesBean.initPantalla}" />
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.propCul_Ingresar}" />
													</f:facet>
													<center><h:panelGrid columns="2"
														columnClasses="textoPlano,tectoPlano"
														style=" width : 408px;">

														<h:outputLabel value="#{text.propCul_ComboCultivo}"
															styleClass="textoPlano" />
														<rich:comboBox
															value="#{propiedadesBean.cultivoSeleccionado}"
															enableManualInput="false" styleClass="combo"
															disabled="#{propiedadesBean.disableSeleccionCultivo}"
															width="248px">
															<f:selectItems value="#{propiedadesBean.cultivos}" />
															<a4j:support
																action="#{propiedadesBean.TakeSelectionCultivo}"
																event="onchange" ajaxSingle="true"
																reRender="panelPropiedades" />
														</rich:comboBox>
													</h:panelGrid></center>
													<h:panelGroup>
														<h:panelGrid id="panelPropiedades">
															<h:panelGrid>
																<center><rich:dataTable border="2"
																	width="400px" rows="5" styleClass="textoDataTable"
																	id="tablaPropiedades"
																	value="#{propiedadesBean.listaPropiedades}"
																	var="propiedad" rowKeyVar="row"
																	headerClass="columnHeader" rowClasses="oddRow,evenRow">

																	<f:facet name="header">
																		<h:outputText value="#{text.propCul_ListaProp}" />
																	</f:facet>

																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="#{text.propiedad_Codigo}" />
																		</f:facet>
																		<h:outputText value="#{propiedad._codigo}" id="codigo" />
																	</rich:column>

																	<rich:column width="220">
																		<f:facet name="header">
																			<h:outputText value="#{text.propiedad_Nombre}" />
																		</f:facet>
																		<h:outputText value="#{propiedad._nombre}" id="nombre" />
																	</rich:column>

																	<rich:column width="320">
																		<f:facet name="header">
																			<h:outputText value="#{text.propiedad_UnidadMedida}" />
																		</f:facet>
																		<h:outputText value="#{propiedad._unidadMedida}"
																			id="descripcion" />
																	</rich:column>

																	<rich:column width="80">
																		<f:facet name="header">
																			<h:outputText value="Eliminar ingreso" />
																		</f:facet>

																		<center><a4j:commandButton
																			action="#{propiedadesBean.EliminarPropiedad}"
																			image="/Recursos/Imagenes/Iconos/delete.gif"
																			immediate="true" disabled="#{propiedad._grabada}"
																			style=" border:0; width : 27px; height : 21px;"
																			reRender="panelPropiedades">
																			<a4j:actionparam name="propiedadElegida"
																				value="#{propiedad._codigo}" />
																			<rich:toolTip value="Elimina solo las agregadas" />
																		</a4j:commandButton></center>

																	</rich:column>
																	<f:facet name="footer">
																		<rich:datascroller renderIfSinglePage="false"
																			maxPages="6" />
																	</f:facet>
																</rich:dataTable></center>
															</h:panelGrid>

															<center><h:panelGrid columns="2"
																id="datosPropiedad"
																columnClasses="textoPlano,textoPlano">

																<h:outputText value="Código" />
																<h:inputText value="#{propiedadesBean.codigo}"
																	required="true"
																	requiredMessage="Ingrese un código para la propiedad"
																	styleClass="textoPlano" maxlength="10"
																	onkeypress="ValidarCampoLetras(this, event)"
																	style=" width : 230px;" />

																<h:outputText value="Nombre" />
																<h:inputText value="#{propiedadesBean.nombre}"
																	required="true"
																	requiredMessage="Ingrese un nombre para la propiedad"
																	styleClass="textoPlano" maxlength="220"
																	onkeypress="ValidarCampoLetras(this, event)"
																	style=" width : 230px;">
																</h:inputText>

																<h:outputText value="Unidad de Medida" />
																<h:inputText value="#{propiedadesBean.unidadedida}"
																	styleClass="textoPlano" maxlength="220"
																	
																	style=" width : 230px;">
																</h:inputText>

																<h:outputLabel value="Seleccionar un estado" />
																<rich:comboBox
																	value="#{propiedadesBean.tipoSeleccionado}"
																	enableManualInput="false" styleClass="combo"
																	width="230px">
																	<f:selectItems
																		value="#{propiedadesBean.tipoPropiedades}" />
																</rich:comboBox>

																<h:outputText />
																<a4j:commandButton
																	style="font-size: 10pt; color: #2d77c2; width : 123px;"
																	styleClass="textoPlano"
																	value="#{text.boton_IngresarProp}"
																	disabled="#{propiedadesBean.disableAceptarPropiedad}">
																	<a4j:support event="onclick" ajaxSingle="false"
																		reRender="panelPropiedades"
																		action="#{propiedadesBean.AceptarPropiedad}" />
																</a4j:commandButton>
															</h:panelGrid></center>
														</h:panelGrid>
														<td></td>

														<center><h:panelGrid columns="2">
															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 120px;"
																styleClass="textoPlano"
																action="#{propiedadesBean.ReigstrarPropiedades}"
																value="#{text.boton_Registrar}" />

															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 120px;"
																styleClass="textoPlano" action="cancelar"
																value="#{text.boton_Cerrar}" />
														</h:panelGrid></center>
													</h:panelGroup>
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
															value="#{cultivoBean.exito}" />
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