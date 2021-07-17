import React, {useState,useEffect} from 'react'
import { NavLink, useParams } from 'react-router-dom'
import { Header, Image, Table } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService'

export default function AdvertisementDetail() {

    let {id} = useParams()

    const [advertisement, setAdvertisement] = useState([])

    useEffect(() => {
      let advertisementService = new AdvertisementService()
      advertisementService.getJobAdvertisementById(id).then(result => setAdvertisement(result.data.data))
    },[])

    
    return (
        <div>
            {advertisement!=null&&advertisement.approved==true&&advertisement.jobAdvertisementOpen==true?
  <Table basic='very' celled collapsing>
    <Table.Header>
      <Table.Row>
        <Table.HeaderCell>İş açıklaması</Table.HeaderCell>
        <Table.HeaderCell>İş pozisyonu</Table.HeaderCell>
        <Table.HeaderCell>Açık pozisyon</Table.HeaderCell>
        <Table.HeaderCell>Taban maaş</Table.HeaderCell>
        <Table.HeaderCell>Tavan maaş</Table.HeaderCell>
        <Table.HeaderCell>Şehir</Table.HeaderCell>
        <Table.HeaderCell>Yayınlanma tarihi</Table.HeaderCell>
        <Table.HeaderCell>Son başvuru tarihi</Table.HeaderCell>
        <Table.HeaderCell icon="arrow circle left" as={NavLink} to="/AdvertisementList" ></Table.HeaderCell>
      </Table.Row>
    </Table.Header>

    <Table.Body>
      <Table.Row>
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
              {advertisement.jobPosition!=null?advertisement.jobPosition.name:''}
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
              {advertisement.city!=null?advertisement.city.city:''}
            </Header.Content>
          </Header>
        </Table.Cell>

        <Table.Cell>
          <Header as='h4'>
            <Header.Content>
              {advertisement.releaseDate}
            </Header.Content>
          </Header>
        </Table.Cell>

        <Table.Cell>
          <Header as='h4'>
            <Header.Content>
              {advertisement.applicationDeadline}
            </Header.Content>
          </Header>
        </Table.Cell>


      </Table.Row>
      
      </Table.Body>

    
        



    </Table>
    :"İş ilanı bulunamadı"}

        </div>
    )
}
