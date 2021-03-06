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
package org.fenixedu.academic.service.services.masterDegree.administrativeOffice.candidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.fenixedu.academic.domain.DegreeCurricularPlan;
import org.fenixedu.academic.domain.ExecutionDegree;
import org.fenixedu.academic.dto.InfoExecutionDegree;
import org.fenixedu.academic.service.services.exceptions.NotAuthorizedException;

import pt.ist.fenixframework.FenixFramework;

/**
 * 
 * @author <a href="mailto:amam@mega.ist.utl.pt">Amin Amirali</a>
 * @author <a href="mailto:frnp@mega.ist.utl.pt">Francisco Paulo</a>
 * 
 */

/*
 * Given the id of a degreeCurricularPlan, this service returns its
 * executionDegree persistentSupportecified in executionDegreeIndex
 */

public class ReadExecutionDegreeByDegreeCurricularPlanID {

    protected InfoExecutionDegree run(String degreeCurricularPlanID, Integer executionDegreeIndex) {
        List infoExecutionDegreeList = null;
        Collection executionDegrees = null;

        DegreeCurricularPlan degreeCurricularPlan = FenixFramework.getDomainObject(degreeCurricularPlanID);

        executionDegrees = degreeCurricularPlan.getExecutionDegreesSet();

        infoExecutionDegreeList = new ArrayList();

        for (Iterator iter = executionDegrees.iterator(); iter.hasNext();) {
            ExecutionDegree executionDegree = (ExecutionDegree) iter.next();

            InfoExecutionDegree infoExecutionDegree = InfoExecutionDegree.newInfoFromDomain(executionDegree);
            infoExecutionDegreeList.add(infoExecutionDegree);
        }

        Collections.sort(infoExecutionDegreeList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((InfoExecutionDegree) o2).getInfoExecutionYear().getBeginDate()
                        .compareTo(((InfoExecutionDegree) o1).getInfoExecutionYear().getBeginDate());
            }
        });

        return ((InfoExecutionDegree) infoExecutionDegreeList.get(executionDegreeIndex.intValue() - 1));
    }

    /**
     * @author <a href="mailto:shezad@ist.utl.pt">Shezad Anavarali</a>
     * 
     * @param degreeCurricularPlanID
     * @param executionDegree
     * @return
     * @throws ExcepcaoPersistencia
     */
    protected InfoExecutionDegree run(String degreeCurricularPlanID, final String executionYear) {
        DegreeCurricularPlan degreeCurricularPlan = FenixFramework.getDomainObject(degreeCurricularPlanID);

        if (executionYear.equals("")) {
            return InfoExecutionDegree.newInfoFromDomain(degreeCurricularPlan.getExecutionDegreesSet().iterator().next());
        }

        ExecutionDegree executionDegree =
                (ExecutionDegree) CollectionUtils.find(degreeCurricularPlan.getExecutionDegreesSet(), new Predicate() {

                    @Override
                    public boolean evaluate(Object arg0) {
                        ExecutionDegree executionDegree = (ExecutionDegree) arg0;
                        if (executionDegree.getExecutionYear().getYear().equals(executionYear)) {
                            return true;
                        }
                        return false;
                    }
                });

        return InfoExecutionDegree.newInfoFromDomain(executionDegree);
    }

    // Service Invokers migrated from Berserk

    private static final ReadExecutionDegreeByDegreeCurricularPlanID serviceInstance =
            new ReadExecutionDegreeByDegreeCurricularPlanID();

    public static InfoExecutionDegree runReadExecutionDegreeByDegreeCurricularPlanID(String degreeCurricularPlanID,
            Integer executionDegreeIndex) throws NotAuthorizedException {
        return serviceInstance.run(degreeCurricularPlanID, executionDegreeIndex);
    }

    public static InfoExecutionDegree runReadExecutionDegreeByDegreeCurricularPlanID(String degreeCurricularPlanID,
            final String executionYear) throws NotAuthorizedException {
        return serviceInstance.run(degreeCurricularPlanID, executionYear);
    }

}