/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Pre Bolonha.
 *
 * FenixEdu IST Pre Bolonha is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Pre Bolonha is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Pre Bolonha.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on Oct 14, 2003
 *
 */
package org.fenixedu.academic.dto;

import java.sql.Timestamp;
import java.util.List;

import org.fenixedu.academic.domain.MasterDegreeThesisDataVersion;
import org.fenixedu.academic.util.State;

/**
 * @author : - Shezad Anavarali (sana@mega.ist.utl.pt) - Nadir Tarmahomed
 *         (naat@mega.ist.utl.pt)
 * 
 */
public class InfoMasterDegreeThesisDataVersion extends InfoObject {

    private InfoMasterDegreeThesis infoMasterDegreeThesis;

    private List<InfoTeacher> infoAssistentGuiders;

    private List<InfoTeacher> infoGuiders;

    private String dissertationTitle;

    private Timestamp lastModification;

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setDissertationTitle(String dissertationTitle) {
        this.dissertationTitle = dissertationTitle;
    }

    public String getDissertationTitle() {
        return dissertationTitle;
    }

    public void setInfoAssistentGuiders(List<InfoTeacher> infoAssistentGuiders) {
        this.infoAssistentGuiders = infoAssistentGuiders;
    }

    public List<InfoTeacher> getInfoAssistentGuiders() {
        return infoAssistentGuiders;
    }

    public void setInfoGuiders(List<InfoTeacher> infoGuiders) {
        this.infoGuiders = infoGuiders;
    }

    public List<InfoTeacher> getInfoGuiders() {
        return infoGuiders;
    }

    public void setInfoMasterDegreeThesis(InfoMasterDegreeThesis infoMasterDegreeThesis) {
        this.infoMasterDegreeThesis = infoMasterDegreeThesis;
    }

    public InfoMasterDegreeThesis getInfoMasterDegreeThesis() {
        return infoMasterDegreeThesis;
    }

    public void setLastModification(Timestamp lastModification) {
        this.lastModification = lastModification;
    }

    public Timestamp getLastModification() {
        return lastModification;
    }

    @Override
    public String toString() {
        String result = "[" + this.getClass().getName() + ": \n";
        result += "externalId = " + getExternalId() + "; \n";
        result += "infoMasterDegreeThesis = " + this.infoMasterDegreeThesis.getExternalId() + "; \n";
        result += "infoAssistentGuiders = " + this.infoAssistentGuiders.toString() + "; \n";
        result += "infoGuiders" + this.infoGuiders.toString() + "; \n";
        result += "dissertationTitle = " + this.dissertationTitle.toString() + "; \n";
        result += "lastModification = " + this.lastModification.toString() + "; \n";
        result += "currentState = " + this.currentState.toString() + "; \n";
        result += "] \n";

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof InfoMasterDegreeThesisDataVersion) {
            InfoMasterDegreeThesisDataVersion infoMasterDegreeThesisDataVersion = (InfoMasterDegreeThesisDataVersion) obj;
            result =
                    this.infoMasterDegreeThesis.equals(infoMasterDegreeThesisDataVersion.getInfoMasterDegreeThesis())
                            && this.lastModification.equals(infoMasterDegreeThesisDataVersion.getLastModification());
        }
        return result;
    }

    public static InfoMasterDegreeThesisDataVersion newInfoFromDomain(MasterDegreeThesisDataVersion masterDegreeThesisDataVersion) {
        InfoMasterDegreeThesisDataVersion infoMasterDegreeThesisDataVersion = null;
        if (masterDegreeThesisDataVersion != null) {
            infoMasterDegreeThesisDataVersion = new InfoMasterDegreeThesisDataVersion();
            infoMasterDegreeThesisDataVersion.copyFromDomain(masterDegreeThesisDataVersion);
        }
        return infoMasterDegreeThesisDataVersion;
    }

    public void copyFromDomain(MasterDegreeThesisDataVersion masterDegreeThesisDataVersion) {
        super.copyFromDomain(masterDegreeThesisDataVersion);
        if (masterDegreeThesisDataVersion != null) {
            setCurrentState(masterDegreeThesisDataVersion.getCurrentState());
            setDissertationTitle(masterDegreeThesisDataVersion.getDissertationTitle());
            if (masterDegreeThesisDataVersion.getLastModification() != null) {
                this.setLastModification(new Timestamp(masterDegreeThesisDataVersion.getLastModification().getTime()));
            }
        }
    }

}