import React from 'react';
import Counter from '../components/Counter';

// More on default export: https://storybook.js.org/docs/react/writing-stories/introduction#default-export
export default {
    title: 'Example/Counter',
    component: Counter,
  // More on argTypes: https://storybook.js.org/docs/react/api/argtypes
    argTypes: {
        onIncrease: {action: "increased"}
    },
};
const Template = (args) => <Counter {...args} />;

export const Default = Template.bind({});
