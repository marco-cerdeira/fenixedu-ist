<%--

    Copyright © 2013 Instituto Superior Técnico

    This file is part of FenixEdu IST Integration.

    FenixEdu IST Integration is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu IST Integration is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu IST Integration.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>

<em><bean:message key="label.academicAdminOffice" bundle="ACADEMIC_OFFICE_RESOURCES"/></em>
<h2><bean:message key="label.curriculum.validation.set.end.stage.date" bundle="ACADEMIC_OFFICE_RESOURCES" /></h2>

<bean:define id="studentCurricularPlanId" name="studentCurricularPlan" property="externalId"/>

<p>
	<html:link page="<%= "/curriculumValidation.do?method=prepareCurriculumValidation&amp;studentCurricularPlanId=" + studentCurricularPlanId  %>">
		<bean:message key="label.back" bundle="ACADEMIC_OFFICE_RESOURCES" />
	</html:link>
</p>

<logic:equal name="studentCurriculumValidationAllowed" value="false">
	<bean:message key="message.curriculum.validation.not.allowed" bundle="ACADEMIC_OFFICE_RESOURCES" />
</logic:equal>

<logic:equal name="studentCurriculumValidationAllowed" value="true">
	<fr:form action="<%= "/curriculumValidation.do?method=editEndStageDate&amp;studentCurricularPlanId=" + studentCurricularPlanId %>">
		 <fr:edit id="student.enrolment.bean" name="bolonhaStudentEnrollmentBean" layout="tabular-editable">
			 <fr:schema type="org.fenixedu.academic.dto.student.enrollment.bolonha.BolonhaStudentEnrollmentBean" bundle="ACADEMIC_OFFICE_RESOURCES">
				<fr:slot name="endStageDate" key="label.curriculum.validation.end.stage.date" >
					<fr:validator name="pt.ist.fenixWebFramework.renderers.validators.DateValidator">
						<fr:property name="dateFormat" value="dd/MM/yyyy" />
						<fr:property name="message" value="error.invalid.date.format" />
						<fr:property name="key" value="true" />
					</fr:validator>
				</fr:slot>
			</fr:schema>
		 	<fr:layout>
				<fr:property name="classes" value="tstyle5 thlight"/>
		        <fr:property name="columnClasses" value=",,tdclear tderror1"/>					                	 	
		 	</fr:layout>
		 </fr:edit>
		 
		 <html:submit><bean:message key="label.submit" bundle="ACADEMIC_OFFICE_RESOURCES" /></html:submit>
	 </fr:form> 
</logic:equal>
