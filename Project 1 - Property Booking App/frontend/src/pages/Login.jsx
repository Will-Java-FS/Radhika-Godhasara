import { useState } from 'react';
import { Link as ReactRouterLink, useNavigate } from "react-router-dom";
import { Link as ChakraLink, FormControl, FormLabel, FormErrorMessage, Input, Button, Heading, Flex, Box } from '@chakra-ui/react'
import axios from "axios";
import AuthenticationService from "../components/AuthenticationService";

export default function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [loginFailed, setLoginFailed] = useState(false)

    const loginClicked = async (event) => {
        event.preventDefault();

        AuthenticationService.axiosToken();
        await axios.post("http://localhost:8080/authenticate", {
                username: username,
                passwordHash: password
        })
        .then(response => {
            if (response.status == 200) {
                console.log(response.data)
                setLoginFailed(false);
                if (response.data.role === "renter") {AuthenticationService.loginRenter(response.data.username, response.data.userId);}
                else {AuthenticationService.loginOwner(response.data.username, response.data.userId);}
                AuthenticationService.setUpToken(response.data.token);
                navigate('/redirect');
            } else {
                setLoginFailed(true);
            }
        })
        .catch(error => {
            console.error('Error on login attempt!', error);
        });
    }

    const userEmptyError = username === '';
    const passEmptyError = password === '';
    
    return (
        <Flex width="full" align="center" justifyContent="center">
            <form onSubmit={loginClicked}>
                <Box p='5' textAlign='center'>
                    <Heading size='lg'>Login</Heading>
                </Box>
                <FormControl isRequired>
                    <FormLabel>Username</FormLabel>
                    <Input type = "text" placeholder = "Enter Username" onChange={event => setUsername(event.currentTarget.value)} />
                    {userEmptyError && loginFailed ? <FormErrorMessage>Username is required.</FormErrorMessage> : null}
                </FormControl>
                <FormControl isRequired>
                    <FormLabel>Password</FormLabel>
                    <Input type = "password" placeholder = "Enter Password" onChange={event => setPassword(event.currentTarget.value)} />
                    {passEmptyError && loginFailed ? <FormErrorMessage>Password is required.</FormErrorMessage> : null}
                </FormControl>
                <br/>
                <Button type = "submit">Login</Button>
                <ChakraLink as={ReactRouterLink} to="/register"> Dont have an account? Sign up here.</ChakraLink>
                {loginFailed ? <FormErrorMessage>Login failed.</FormErrorMessage> : null}
            </form>
        </Flex>
    )
}