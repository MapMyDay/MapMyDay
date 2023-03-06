import Button from "../components/Button";
// import { Button } from './Button';

export default {
    title: "Component/Button",
    component: Button,
    argTypes: {
        onClick: {action: "onClick"},
    },
};

export const Default = (args) => {
    return <Button {...args}>Button</Button>;
};