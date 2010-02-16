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
									<h:outputText
										value="Bienvenido #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}. Ultimo acceso #{loginBean.usuario._ultimoAcceso}" />
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:panelGrid>
										<center><h:outputText styleClass="mensajeError"
											style="font-size: 12pt" value="#{text.login_notLogged}" /></center>
									</h:panelGrid>
									<h:panelGrid columns="2">
										<center><a4j:commandButton
											style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" action="#{loginBean.logout}"
											value="#{text.login_login}" /></center>
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
											<h:panelGroup rendered="#{regionBean.init}" />
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.region_ListaRegiones}" />
													</f:facet>

													<center><rich:dataTable border="2" width="650px"
														rows="6" styleClass="textoDataTable" id="tablaRegiones"
														value="#{regionBean.regiones}" var="region"
														rowKeyVar="row" headerClass="columnHeader"
														rowClasses="oddRow,evenRow">

														<f:facet name="header">
															<h:outputText value="#{text.region_ListaRegiones}" />
														</f:facet>

														<rich:column width="50">
															<f:facet name="header">
																<h:outputText value="#{text.region_Codigo}" />
															</f:facet>
															<h:outputText value="#{region._codigo}" id="codigo" />
														</rich:column>

														<rich:column width="220">
															<f:facet name="header">
																<h:outputText value="#{text.region_Nombre}" />
															</f:facet>
															<h:outputText value="#{region._nombre}" id="nombre" />
														</rich:column>

														<rich:column width="320">
															<f:facet name="header">
																<h:outputText value="#{text.region_Descripcion}" />
															</f:facet>
															<h:outputText value="#{region._descripcion}"
																id="descripcion" />
														</rich:column>

														<rich:column width="60">
															<f:facet name="header">
																<h:outputText value="Acciones" />
															</f:facet>

															<a4j:commandButton action="#{regionBean.verRegiones}"
																image="/Recursos/Imagenes/Iconos/edit.gif"
																style="width : 27px; height : 21px;">
																<a4j:actionparam name="regionElegida"
																	value="#{region._id}" />
																<rich:toolTip value="Modificar" />
															</a4j:commandButton>

															<a4j:commandButton action="#{regionBean.eliminar}"
																image="/Recursos/Imagenes/Iconos/delete.gif"
																style=" border:0; width : 27px; height : 21px;">
																<a4j:actionparam name="regionEliminar"
																	value="#{region._id}" />
																<rich:toolTip value="Eliminar" />
															</a4j:commandButton>

														</rich:column>
														<f:facet name="footer">
															<rich:datascroller renderIfSinglePage="false"
																maxPages="6" />
														</f:facet>
													</rich:dataTable></center>
													<br></br>

													<center><h:panelGrid columns="3">
														<a4j:commandButton immediate="true"
															style="font-size: 10pt; color: #2d77c2; width : 87px;"
															styleClass="textoPlano" action="#{regionBean.alta}"
															value="#{text.boton_Registrar}" />
														<a4j:commandButton immediate="true"
															style="font-size: 10pt; color: #2d77c2; width : 87px;"
															styleClass="textoPlano" action="cancelar"
															value="#{text.perfil_Cerrar}" />
													</h:panelGrid></center>

													<center><h:panelGrid>
														<h:outputText styleClass="textoPlano"
															value="#{regionBean.exito}" />
													</h:panelGrid></center>
												</rich:panel>
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