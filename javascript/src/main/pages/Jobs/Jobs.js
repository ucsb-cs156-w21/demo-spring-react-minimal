import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import JobLauncher from "main/components/Jobs/JobLauncher";

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
  const { data: jobs } = useSWR(["/api/jobs", getToken], fetchWithToken);

  console.log("jobs=", jobs);

  return (
    <>
      <JobLauncher jobs={jobs} />
    </>
  );
};

export default Jobs;
