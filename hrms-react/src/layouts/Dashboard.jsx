import React from 'react'
import AdvertisementList from '../pages/AdvertisementList';
import EmployerList from '../pages/EmployerList';
import JobSeekerList from '../pages/JobSeekerList';
import CvDetail from '../pages/CvDetail';
import { Route } from 'react-router-dom';
import AdvertisementDetail from '../pages/AdvertisementDetail';
import CvList from '../pages/CvList';
import { Grid, GridColumn, Container } from 'semantic-ui-react';
import Categories from './Categories';
import AdvertisementApprove from '../pages/AdvertisementApprove';
import { ToastContainer } from 'react-toastify';
import EmployerAdd from '../pages/EmployerAdd';
import JobAdvertisementAdd from '../pages/JobAdvertisementAdd';
import JobSeekerAdd from '../pages/JobSeekerAdd';
import CvAdd from '../pages/CvAdd';
import CvEdit from '../pages/CvEdit';
import SystemStaffEdit from '../pages/SystemStaffEdit';
import AdvertisementUpdate from '../pages/AdvertisementUpdate';
import EmployerEdit from '../pages/EmployerEdit';
import EmployerUpdateApprove from '../pages/EmployerUpdateApprove';



export default function Dashboard() {
    return (
        <div>
            <ToastContainer position="bottom-right"></ToastContainer>
            <Container className="main">
            <Grid>
                <Grid.Row >
                    <Grid.Column width={5}>
                        <Categories />
                    </Grid.Column>
 
                    
                        <GridColumn width={11}>

                            <Route exact path="/" component={AdvertisementList}></Route>
                            <Route exact path="/AdvertisementList/p/:page" component={AdvertisementList}></Route>
                            <Route exact path="/AdvertisementList/:id" component={AdvertisementDetail}></Route>
                            <Route exact path="/JobSeeker/Cv/Edit" component={CvEdit}></Route>
                            <Route exact path="/CvList" component={CvList}></Route>
                            <Route exact path="/CvDetail/:id" component={CvDetail}></Route>
                            <Route exact path="/Advertisement/Approve" component={AdvertisementApprove}></Route>
                            <Route exact path="/Advertisement/Edit" component={AdvertisementUpdate}></Route>
                            <Route exact path="/EmployerList" component={EmployerList}></Route>
                            <Route exact path="/Employer/Add" component={EmployerAdd}></Route>
                            <Route exact path="/Employer/Edit" component={EmployerEdit}></Route>
                            <Route exact path="/Employer/ApproveUpdates" component={EmployerUpdateApprove}></Route>
                            <Route exact path="/JobAdvertisement/Add" component={JobAdvertisementAdd}></Route>
                            <Route exact path="/JobSeekerList" component={JobSeekerList}></Route>
                            <Route exact path="/JobSeeker/Add" component={JobSeekerAdd}></Route>
                            <Route exact path="/JobSeeker/Cv/Add" component={CvAdd}></Route>
                            <Route exact path="/SystemStaff/Edit" component={SystemStaffEdit}></Route>
                        </GridColumn>
                   


                </Grid.Row>
            </Grid>
            </Container>
        </div>
    )
}
