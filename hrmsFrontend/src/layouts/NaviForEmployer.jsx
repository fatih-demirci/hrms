import React from 'react'
import { Menu,Container } from 'semantic-ui-react'
export default function NaviForEmployer() {
    return (
        <div>
            
         <Menu inverted fixed="top" size="tiny">
         <Container>
        <Menu.Item>
          <img src='/logo192.png' />
        </Menu.Item>

        <Menu.Item
          name='profile'

        >
          Profile
        </Menu.Item>

        <Menu.Item
          name='Add Advertisement'

        >
          Add Advertisement
        </Menu.Item>

        <Menu.Item
          name='view cv'

        >
          View Cv's
        </Menu.Item>
        </Container>
      </Menu>
        </div>
    )
}
