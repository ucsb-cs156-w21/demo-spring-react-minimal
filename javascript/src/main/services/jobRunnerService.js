import { fetchWithToken } from "main/utils/fetch";

const buildRunJob = (getToken, onSuccess, onError) => {
  const func = async (key, object) => {
    try {
      const result = await fetchWithToken(
        `/api/admin/jobs/run/${key}`,
        getToken,
        {
          method: "POST",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify(object),
        }
      );
      onSuccess(result);
    } catch (err) {
      onError(err);
    }
  };
  return func;
};

export { buildRunJob };
