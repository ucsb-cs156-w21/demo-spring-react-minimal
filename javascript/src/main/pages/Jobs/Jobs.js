import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import JobRunner from "main/components/Jobs/JobRunner";
import { buildRunJob } from "main/services/jobRunnerService";

import useSWR from "swr";
import { fetchWithToken } from "main/utils/fetch";

const jobs = [
  {
    key: "SampleKey",
    description: "Sample Description",
  },
  {
    key: "SampleKey2",
    description: "Sample Description2",
  },
];

const Jobs = () => {
  const { getAccessTokenSilently: getToken } = useAuth0();
  const { data: jobs } = useSWR(["/api/admin/jobs", getToken], fetchWithToken);

  console.log("jobs=", jobs);

  return (
    <>
      <JobRunner jobs={jobs} />
    </>
  );
};

export default Jobs;
