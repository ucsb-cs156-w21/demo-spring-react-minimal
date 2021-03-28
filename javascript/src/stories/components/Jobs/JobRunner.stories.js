import React from "react";

import JobRunner from "main/components/Jobs/JobRunner";

export default {
  title: "components/Jobs/JobRunner",
  component: JobRunner,
};

const Template = (args) => <JobRunner {...args} />;

export const Empty = Template.bind({});
Empty.args = {
  jobs: [],
};

export const SeveralJobs = Template.bind({});
SeveralJobs.args = {
  jobs: [
    {
      key: "FakeJob1",
    },
    {
      key: "FakeJob2",
    },
    {
      key: "FakeJob3",
    },
  ],
};
