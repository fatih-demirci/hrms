import React from 'react'
import { Menu,Container } from 'semantic-ui-react'
export default function NaviForJobSeeker() {
    return (
        <div>
        <Menu inverted fixed="top" size="small">
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
          name='create cv'

        >
          Create cv
        </Menu.Item>

        <Menu.Item
          name='advertisements'

        >
          Advertisements
        </Menu.Item>
        </Container>
      </Menu>
        </div>
    )
}
