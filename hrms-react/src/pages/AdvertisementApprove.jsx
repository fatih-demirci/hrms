import { render } from '@testing-library/react'
import React, { useState, useEffect } from 'react'
import { Link, NavLink, useHistory, useParams } from 'react-router-dom'
import { toast } from 'react-toastify'
import { Header, Button, Table, Grid } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'
import EmployerService from '../services/employerService'

export default function AdvertisementApprove() {

    const [employers, setEmployers] = useState([])

    useEffect(() => {
        let employerService = new EmployerService()
       
        employerService.getEmployers().then(result => setEmployers(result.data.data))
    }, [])

    let advertisementService = new AdvertisementService()

    const history = useHistory()
    const setApprove = (jobAdvertisementId, isJobAdvertisementApprove) => {
        toast.success(`Onay Değiştirildi`)

        advertisementService.jobAdvertisementApprove(jobAdvertisementId, isJobAdvertisementApprove).then(()=> history.push("/AdvertisementList")  )
    }
    const jobAdvertisementUpdateApprove =(jobAdvertisementId) =>{
        toast.success(`Güncelleme Onaylandı`)
        advertisementService.jobAdvertisementUpdateApprove(jobAdvertisementId)
    }
    
    function formatDate(date) {
        date = String(date).split('T');
        return date[0]
    }

    return (
        <div>
<Grid centered>
            Onay Verilmemiş İş ilanları
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Şirket adı</Table.HeaderCell>
                        <Table.HeaderCell>İş açıklaması</Table.HeaderCell>
                        <Table.HeaderCell>İş pozisyonu</Table.HeaderCell>
                        <Table.HeaderCell>Açık pozisyon</Table.HeaderCell>
                        <Table.HeaderCell>Taban maaş</Table.HeaderCell>
                        <Table.HeaderCell>Tavan maaş</Table.HeaderCell>
                        <Table.HeaderCell>Şehir</Table.HeaderCell>
                        <Table.HeaderCell>Yayınlanma tarihi</Table.HeaderCell>
                        <Table.HeaderCell>Son başvuru tarihi</Table.HeaderCell>

                    </Table.Row>
                </Table.Header>

                <Table.Body >
                    {employers.map(employer => (


                        employer.jobAdvertisements != null  ?
                            employer.jobAdvertisements.map(advertisement => (

                                advertisement.approved === false && advertisement.exJobAdvertisementId===0 ?
                                    <Table.Row key={advertisement.id}>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {employer.companyName}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobDescription}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobPosition != null ? advertisement.jobPosition.name : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.openPositions}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.minSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.maxSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.city != null ? advertisement.city.city : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.releaseDate)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.applicationDeadline)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Button className="buttonMarginLeft"  onClick= {() => setApprove(advertisement.id, true)} primary >Onayla {advertisement.id} </Button>
                                        </Table.Cell>


                                    </Table.Row>

                                    :
                                    <Table.Row key={advertisement.id}>

                                    </Table.Row>

                            ))

                            : ''



                    )

                    )

                    }
                </Table.Body>
            </Table>







            Güncelleme Bekleyen İş İlanları
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Şirket adı</Table.HeaderCell>
                        <Table.HeaderCell>İş açıklaması</Table.HeaderCell>
                        <Table.HeaderCell>İş pozisyonu</Table.HeaderCell>
                        <Table.HeaderCell>Açık pozisyon</Table.HeaderCell>
                        <Table.HeaderCell>Taban maaş</Table.HeaderCell>
                        <Table.HeaderCell>Tavan maaş</Table.HeaderCell>
                        <Table.HeaderCell>Şehir</Table.HeaderCell>
                        <Table.HeaderCell>Yayınlanma tarihi</Table.HeaderCell>
                        <Table.HeaderCell>Son başvuru tarihi</Table.HeaderCell>

                    </Table.Row>
                </Table.Header>

                <Table.Body >
                    {employers.map(employer => (


                        employer.jobAdvertisements != null ?
                            employer.jobAdvertisements.map(advertisement => (

                                advertisement.approved === false && advertisement.exJobAdvertisementId!==0 ?
                                    <Table.Row key={advertisement.id}>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {employer.companyName}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobDescription}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobPosition != null ? advertisement.jobPosition.name : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.openPositions}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.minSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.maxSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.city != null ? advertisement.city.city : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.releaseDate)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.applicationDeadline)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Button className="buttonMarginLeft"  onClick= {() =>jobAdvertisementUpdateApprove(advertisement.id)} primary >Güncellemeyi Onayla {advertisement.id} </Button>
                                        </Table.Cell>


                                    </Table.Row>

                                    :
                                    <Table.Row key={advertisement.id}>

                                    </Table.Row>

                            ))

                            : ''



                    )

                    )

                    }
                </Table.Body>
            </Table>













            Onaylı iş ilanları
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Şirket adı</Table.HeaderCell>
                        <Table.HeaderCell>İş açıklaması</Table.HeaderCell>
                        <Table.HeaderCell>İş pozisyonu</Table.HeaderCell>
                        <Table.HeaderCell>Açık pozisyon</Table.HeaderCell>
                        <Table.HeaderCell>Taban maaş</Table.HeaderCell>
                        <Table.HeaderCell>Tavan maaş</Table.HeaderCell>
                        <Table.HeaderCell>Şehir</Table.HeaderCell>
                        <Table.HeaderCell>Yayınlanma tarihi</Table.HeaderCell>
                        <Table.HeaderCell>Son başvuru tarihi</Table.HeaderCell>

                    </Table.Row>
                </Table.Header>

                <Table.Body >
                    {employers.map(employer => (


                        employer.jobAdvertisements != null ?
                            employer.jobAdvertisements.map(advertisement => (

                                advertisement.approved === true && advertisement.exJobAdvertisementId===0 ?
                                    <Table.Row key={advertisement.id}>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {employer.companyName}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobDescription}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.jobPosition != null ? advertisement.jobPosition.name : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.openPositions}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.minSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.maxSalary}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {advertisement.city != null ? advertisement.city.city : ''}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.releaseDate)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Header as='h4'>
                                                <Header.Content>
                                                    {formatDate(advertisement.applicationDeadline)}
                                                </Header.Content>
                                            </Header>
                                        </Table.Cell>

                                        <Table.Cell>
                                            <Button className="buttonMarginLeft"  onClick= {() => setApprove(advertisement.id, false)} primary >Onayı Geri Al {advertisement.id} </Button>
                                        </Table.Cell>


                                    </Table.Row>

                                    :
                                    <Table.Row key={advertisement.id}>

                                    </Table.Row>

                            ))

                            : ''



                    )

                    )

                    }
                </Table.Body>
            </Table>

            </Grid>
        </div>
    )
}
