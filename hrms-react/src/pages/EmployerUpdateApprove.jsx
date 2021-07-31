import React, { useState, useEffect } from 'react'
import { Button, Grid, Header, Label, Table } from 'semantic-ui-react'
import CompanyNameUpdateService from '../services/companyNameUpdateService'
import EmailUpdateService from '../services/EmailUpdateService'
import EmployerService from '../services/employerService'
import PhoneNumberService from '../services/phoneNumberService'
import WebsiteService from '../services/websiteService'

export default function EmployerUpdateApprove() {

    let companyNameUpdateService = new CompanyNameUpdateService()
    let employerService = new EmployerService()
    let emailUpdateService = new EmailUpdateService()
    let phoneNumberService = new PhoneNumberService()
    let websiteService = new WebsiteService()

    const [employers, setEmployers] = useState([])

    useEffect(() => {
        employerService.getEmployers().then(result => setEmployers(result.data.data))
    }, [])

    function approveCompanyNameUpdate(employerId) {
        companyNameUpdateService.approve(employerId)
    }

    function approveEmailUpdate(employerId) {
        emailUpdateService.approve(employerId)
    }

    function approvePhoneNumberUpdate(employerId,phoneNumberId) {
        phoneNumberService.approve(employerId,phoneNumberId)
    }

    function approveWebsiteUpdate(employerId,websiteId) {
        websiteService.approve(employerId,websiteId)
    }

    return (
        <div>
            <Grid centered>
                <Table basic='very' celled collapsing>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Şirket adı</Table.HeaderCell>
                            <Table.HeaderCell>Telefon Numaraları</Table.HeaderCell>
                            <Table.HeaderCell>Web Siteleri</Table.HeaderCell>
                            <Table.HeaderCell>E-mail</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>

                    <Table.Body>
                        {employers.map(employer => (

                            employer.companyNameUpdate !== null || employer.emailUpdate !== null
                             || employer.phoneNumbers.filter(phoneNumber=>phoneNumber.exPhoneNumberId!==0).length>0
                             || employer.websites.filter(website=>website.exWebsiteId!==0).length>0?
                                <Table.Row key={employer.id}>

                                    <Table.Cell>
                                        <Header as='h4'>
                                            <Header.Content>
                                                <Grid>
                                                    <Grid.Row centered>
                                                        {employer.companyName}
                                                    </Grid.Row>
                                                    {employer.companyNameUpdate !== null ?
                                                    <Grid>
                                                        <Grid.Row centered>
                                                            <Label>{employer.companyNameUpdate.companyNameUpdate} olarak güncellenmesi için personel onayı bekleniyor</Label>                                                           
                                                        </Grid.Row>

                                                        <Grid.Row centered>
                                                            <Button color="green" onClick={() => approveCompanyNameUpdate(employer.id)}>Onayla</Button>
                                                        </Grid.Row>
                                                        </Grid>
                                                        : null}
                                                </Grid>

                                            </Header.Content>
                                        </Header>
                                    </Table.Cell>

                                    <Table.Cell>
                                        <Header as='h4'>
                                            <Header.Content>

                                                {employer.phoneNumbers.map(phoneNumber => (

                                                    phoneNumber.exPhoneNumberId === 0 ?

                                                        <Grid key={phoneNumber.id}>

                                                            <Grid.Row centered>

                                                                {phoneNumber.areaCode} {phoneNumber.phoneNumber + "\n"}
                                                            </Grid.Row>

                                                            {employer.phoneNumbers.filter(updatePhoneNumber => updatePhoneNumber.exPhoneNumberId === phoneNumber.id).map(updatePhoneNumber => (



                                                                <Grid  key={updatePhoneNumber.id}>

                                                                    <Grid.Row centered>
                                                                        <Label>
                                                                            {updatePhoneNumber.areaCode} {updatePhoneNumber.phoneNumber} olarak güncellenmesi için personel onayı bekleniyor
                                                                        </Label>
                                                                    </Grid.Row>

                                                                    <Grid.Row centered>
                                                                        <Button color="green" onClick={() => approvePhoneNumberUpdate(employer.id,phoneNumber.id)}>Onayla</Button>
                                                                    </Grid.Row>




                                                                </Grid>



                                                            ))

                                                            }


                                                        </Grid>


                                                        :
                                                        null
                                                ))}
                                            </Header.Content>
                                        </Header>
                                    </Table.Cell>

                                    <Table.Cell>
                                        <Header as='h4'>
                                            <Header.Content>

                                                {employer.websites.map(website => (
                                                    website.exWebsiteId === 0 ?
                                                        <Grid centered key={website.id}>
                                                            <Grid.Row centered>
                                                                {website.website}
                                                            </Grid.Row>

                                                            {employer.websites.filter(updateWebsite => updateWebsite.exWebsiteId === website.id).map(updateWebsite => (


                                                                <Grid centered key={updateWebsite.id}>
                                                                    <Grid.Row centered>
                                                                        <Label>{updateWebsite.website} olarak güncellenmesi için onay bekleniyor</Label>
                                                                    </Grid.Row>
                                                                    <Grid.Row centered>
                                                                        <Button color="green" onClick={() => approveWebsiteUpdate(employer.id,website.id)}>Onayla</Button>
                                                                    </Grid.Row>
                                                                </Grid>

                                                            ))}

                                                        </Grid>

                                                        : null
                                                ))}
                                            </Header.Content>
                                        </Header>
                                    </Table.Cell>

                                    <Table.Cell>
                                        <Header as='h4'>
                                            <Header.Content>
                                                <Grid>
                                                    <Grid.Row centered>
                                                        {employer.member.email}
                                                    </Grid.Row>
                                                    {employer.emailUpdate !== null ?
                                                    <Grid>
                                                        <Grid.Row centered>
                                                            <Label>{employer.emailUpdate.emailUpdate} olarak güncellenmesi için onay bekleniyor</Label>
                                                           
                                                        </Grid.Row>
                                                        <Grid.Row centered>
                                                        <Button color="green" onClick={() => approveEmailUpdate(employer.id)}>Onayla</Button>
                                                        </Grid.Row>
                                                    </Grid>
                                                        : null
                                                    }
                                                </Grid>


                                            </Header.Content>
                                        </Header>
                                    </Table.Cell>



                                </Table.Row>

                                :
                                <Table.Row key={employer.id}>

                                </Table.Row>

                        )





                        )}




                    </Table.Body>
                </Table>
            </Grid>
        </div>
    )
}
