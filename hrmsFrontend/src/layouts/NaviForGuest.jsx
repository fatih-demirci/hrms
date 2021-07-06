import React from 'react'
import { Menu,Container } from 'semantic-ui-react'
export default function NaviForGuest() {
    return (
        <div>
         <Menu inverted fixed="top" size="tiny">
             <Container>
        <Menu.Item>
          <img src='/logo192.png' />
        </Menu.Item>

        <Menu.Item
          name='sign-in'

        >
          Sign-In
        </Menu.Item>

        <Menu.Item
          name='sign-up'

        >
          Sign-Up
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
