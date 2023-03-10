import Input from "./Input";
import styled from "@emotion/styled";
import Button from "./Button";
// import { useState } from 'react';
import useForm from './../hooks/useForm';
import ErrorText from './ErrorText';
import CardForm from "./CardForm";
import Title from "./Title";

const SignUpForm=({onSubmit})=>{
    const {errors, isLoading, handleChange, handleSubmit } = useForm({
        initialValues: {
            name: "",
            password: "",
            passwordConfirm: "",
        },
        onSubmit,
        validate: ({ name, password, passwordConfirm}) => {
            const newErrors = {};
            if (!name) newErrors.name = "이름을 입력해 주세요.";
            if (!password) newErrors.password = "비밀번호를 입력해 주세요.";
            if (password !== passwordConfirm) newErrors.passwordConfirm="비밀번호가 일치하지 않습니다.";
            return newErrors;

        },
    });

    console.log(errors);

    return(
        <CardForm onSubmit={handleSubmit}>
            <Title>Sign Up</Title>
            <Input type="text" name="name" placeholder="Name" onChange={handleChange} />
            {errors.name && <ErrorText>{errors.name}</ErrorText>}
            <Input type="password" name="password" placeholder="Password" onChange={handleChange} style={{ marginTop: 8}} />
            {errors.password && <ErrorText>{errors.password}</ErrorText>}
            <Input type="password" name="password-confirm" placeholder="Password Confirm" onChange={handleChange} style={{ marginTop: 8}} />
            {errors.passwordConfirm && <ErrorText>{errors.passwordConfirm}</ErrorText>}
            <Button type="submit" disabled={isLoading} style={{ marginTop: 16 }}>Sign Up</Button>
        </CardForm>
    );
}

export default SignUpForm;