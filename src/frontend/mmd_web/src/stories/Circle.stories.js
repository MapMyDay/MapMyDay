import React from 'react';
import Circle from '../components/Circle';

// More on default export: https://storybook.js.org/docs/react/writing-stories/introduction#default-export
export default {
    title: 'Example/Circle',
    component: Circle,
  // More on argTypes: https://storybook.js.org/docs/react/api/argtypes
    argTypes: {
        width: {control: 'number'},
        height: {control: 'number'},
        backgroundColor: { control: 'color' },
    },
};
const Template = (args) => <Circle {...args} />;

export const Default = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
// Primary.args = {
//   primary: true,
//   label: 'Button',
// };