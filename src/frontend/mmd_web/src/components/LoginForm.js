import Input from "./Input";
import styled from "@emotion/styled";
import Button from "./Button";
// import { useState } from 'react';
import useForm from './../hooks/useForm';
import ErrorText from './ErrorText';

const CardForm=styled.form`
    padding: 16px;
    width: 400px;
    background-color: white;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
    box-sizing: border-box;
`;
const Title=styled.h1`
    font-size: 24px;
    text-align: center;
`
const sleep = () => {
    return new Promise((resolve) => {
        setTimeout(() => resolve(), 1000);
    });
};

const LoginForm=({ onSubmit })=>{
    const {errors, isLoading, handleChange, handleSubmit } = useForm({
        initialValues: {
            name: "",
            password: "",
        },
        onSubmit,
        validate: ({ name, password}) => {
            const newErrors = {};
            if (!name) newErrors.name = "이름을 입력해 주세요.";
            if (!password) newErrors.password = "비밀번호를 입력해 주세요.";
            return newErrors;

        },
    });

    console.log(errors);

    return(
        <CardForm onSubmit={handleSubmit}>
            <Title>Login</Title>
            <Input type="text" name="name" placeholder="Name" onChange={handleChange} />
            {errors.name && <ErrorText>{errors.name}</ErrorText>}
            <Input type="password" name="password" placeholder="Password" onChange={handleChange} style={{ marginTop: 8}} />
            {errors.password && <ErrorText>{errors.password}</ErrorText>}
            <Button type="submit" disabled={isLoading} style={{ marginTop: 16 }}>Login</Button>
        </CardForm>
    );
};
export default LoginForm;