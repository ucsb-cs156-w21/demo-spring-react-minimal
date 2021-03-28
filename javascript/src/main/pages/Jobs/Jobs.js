import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import JobRunner from "main/components/Jobs/JobRunner";
import JobRecordTable from "main/components/Jobs/JobRecordTable";

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
  const [page, setPage] = useState(1);
  const [sizePerPage, setSizePerPage] = useState(5);

  const { getAccessTokenSilently: getToken } = useAuth0();
  const { data: jobs } = useSWR(["/api/admin/jobs", getToken], fetchWithToken);
  const { data: jobrecords } = useSWR(
    [
      `/api/admin/jobs/status/paged?page=${
        page - 1
      }&sizePerPage=${sizePerPage}`,
      getToken,
    ],
    fetchWithToken,
    { refreshInterval: 5000 }
  );

  const handleTableChange = (type, { page, sizePerPage }) => {
    console.log("handleTableChange: type=", type);
    console.log("handleTableChange: page=", page, " sizePerPage=", sizePerPage);
    setPage(page);
    setSizePerPage(sizePerPage);
  };

  console.log("Jobs.js jobrecords=", jobrecords);

  return (
    <>
      <JobRunner jobs={jobs} />
      <JobRecordTable
        data={jobrecords}
        page={page}
        sizePerPage={sizePerPage}
        onTableChange={handleTableChange}
        totalSize={(jobrecords && jobrecords.totalElements) || 0}
      />
    </>
  );
};

export default Jobs;
