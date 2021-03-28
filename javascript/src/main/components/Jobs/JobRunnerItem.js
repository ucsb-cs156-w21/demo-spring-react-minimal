import React from "react";
import { Button } from "react-bootstrap";
import { fetchWithToken } from "main/utils/fetch";
import { useAuth0 } from "@auth0/auth0-react";

const JobRunnerItem = ({ job }) => {
  const { getAccessTokenSilently: getToken } = useAuth0();

  const runJob = async (key, object) => {
    console.log(`Run job: key=${key} object=${JSON.stringify(object)}`);
    await fetchWithToken(`/api/admin/jobs/run/${key}`, getToken, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        object,
      }),
    });
  };

  const renderRunButton = (key, object) => {
    return (
      <Button
        variant="danger"
        data-testid="run-button"
        onClick={() => runJob(key, object)}
      >
        Run
      </Button>
    );
  };

  console.log("job=", job);
  return (
    <tr key={job.key}>
      <td>
        <span
          className="job_name"
          data-toggle="tooltip"
          title={`${job.description}`}
        >
          {job.key}
        </span>
      </td>
      <td>TODO: Put last run time here</td>
      <td>{renderRunButton(job.key, {})}</td>
    </tr>
  );
};

export default JobRunnerItem;
