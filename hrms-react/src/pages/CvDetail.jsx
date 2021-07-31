import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { Header, Image, Table, Card, Container, Grid } from 'semantic-ui-react'
import CvService from '../services/cvService'
import SchoolService from '../services/schoolService'

export default function Cv() {

    let {id} = useParams()

    const [cv, setCv] = useState([])

    let cvService = new CvService()
    cvService.getCv(id).then(result => setCv(result.data.data))

    return (
        <div>
            <Container className="main" centered='true'>
                <Grid>
                    {
                        cv != null ?
                            <Table basic='very' celled collapsing centered="true">
                                <Table.Header centered='true'>
                                    <Table.Row>
                                        <Table.HeaderCell>İş Arayan</Table.HeaderCell>
                                        <Table.HeaderCell> </Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>

                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell>
                                            <Header as='h4' image>
                                                <Image src={cv.image != null && cv.image.imageURL != null ? cv.image.imageURL : '/logo192.png'} rounded size='mini' />
                                                <Header.Content>
                                                    {cv.githubAdress + " " + cv.linkedinAdress}
                                                    <Header.Subheader>{cv.description}</Header.Subheader>
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>
                                        <Table.Cell></Table.Cell>



                                    </Table.Row>

                                </Table.Body>
                            </Table>

                            :
                            'Kayıtlı Cv\'si yok'

                    }

                    

                </Grid>

               
                    
                <Container className="main">
                
                <Container className="bottom">
                             <b>Okullar</b>
                        </Container>

                    <Grid>
                        {

                            cv.school != null ?
                                cv.school.map(school => (

                                    <Card key={school.id} centered raised

                                        href='#card-example-link-card'
                                        header={school.schoolName}
                                        meta={school.department}
                                        description={"Okula giriş : "+school.schoolEntryDate + " Mezuniyet tarihi : " + school.graduationDate}
                                    />
                                ))
                                :
                                ''
                        }

                    </Grid>
                    </Container>

                    <Container className="bottom">
                             <b>Bilinen diller</b>
                        </Container>

                    <Grid>
                        {

                            cv.language != null ?
                                cv.language.map(language => (

                                    <Card key={language.id} centered raised 

                                        href='#card-example-link-card'
                                        header={language.languageName}
                                        meta={"Seviye (1-5) : "+language.languageLevel}
                                        
                                    />
                                ))
                                :
                                ''
                        }

                    </Grid>
                    <Container className="top">
                             <b>Bilinen programlama dilleri ve teknolojileri</b>
                        </Container>

                        <Container className="main">
                    <Grid>
                        {

                            cv.programmingLanguageOrTechnology != null ?
                                cv.programmingLanguageOrTechnology.map(program => (

                                    <Card key={program.id} centered raised 

                                        href='#card-example-link-card'
                                        header={program.name}
                                        
                                    />
                                ))
                                :
                                ''
                        }

                    </Grid>
                    
                    </Container>

                        <Container className="bottom">
                             <b>İş tecrübeleri</b>
                        </Container>
                   

                    <Grid>
                        
                        {
                           
                            cv.workExperience != null ?
                                cv.workExperience.map(workExperience => (

                                    <Card key={workExperience.id} centered raised 

                                        href='#card-example-link-card'
                                        header={workExperience.businessName}
                                        meta={workExperience.jobPosition.name}
                                        description={"İşe giriş tarihi "+workExperience.dateOfStart + " İşten ayrılma tarihi " + workExperience.quitDate}
                                    />
                                ))
                                :
                                ''
                        }

                    </Grid>

            </Container>


           
        </div>
    )

}
