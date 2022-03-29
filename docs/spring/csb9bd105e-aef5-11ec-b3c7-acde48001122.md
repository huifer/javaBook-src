# oauth
- http://localhost:9999/login
- http://localhost:9999/oauth/token

```json
{
  "front-version": "2.20.0",
  "version": 3,
  "nodes": [
    {
      "type": "Project",
      "id": "d68a1503-8ef6-4fe6-afa9-627afb1def56",
      "lastModified": "2019-08-27T14:09:30.764+08:00",
      "name": "oauth"
    },
    {
      "type": "Scenario",
      "id": "a47d90b2-4d72-459d-8777-73c3574b9f81",
      "lastModified": "2019-08-27T14:38:25.616+08:00",
      "name": "Scenario 1",
      "requestOrder": [
        "login",
        "/oauth/token"
      ],
      "parentId": "d68a1503-8ef6-4fe6-afa9-627afb1def56"
    },
    {
      "type": "Request",
      "method": {
        "requestBody": true,
        "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
        "name": "POST"
      },
      "body": {
        "formBody": {
          "overrideContentType": true,
          "encoding": "application/x-www-form-urlencoded",
          "items": [
            {
              "enabled": true,
              "type": "Text",
              "name": "username",
              "value": "admin"
            },
            {
              "enabled": true,
              "type": "Text",
              "name": "password",
              "value": "admin"
            }
          ]
        },
        "bodyType": "Form",
        "autoSetLength": true
      },
      "headersType": "Form",
      "uri": {
        "query": {
          "delimiter": "&",
          "items": []
        },
        "scheme": {
          "name": "http",
          "version": "V11"
        },
        "host": "localhost:9999",
        "path": "/login"
      },
      "id": "0037af33-cba1-4bca-ab98-bed03eb35146",
      "lastModified": "2019-08-27T14:00:46.845+08:00",
      "name": "login",
      "headers": [
        {
          "enabled": true,
          "name": "Authorization",
          "value": "Basic YWRtaW46YWRtaW4="
        },
        {
          "enabled": true,
          "name": "Content-Type",
          "value": "application/x-www-form-urlencoded"
        }
      ],
      "metaInfo": {},
      "parentId": "a47d90b2-4d72-459d-8777-73c3574b9f81"
    },
    {
      "type": "Request",
      "method": {
        "requestBody": true,
        "link": "http://tools.ietf.org/html/rfc7231#section-4.3.3",
        "name": "POST"
      },
      "body": {
        "formBody": {
          "overrideContentType": true,
          "encoding": "application/x-www-form-urlencoded",
          "items": [
            {
              "enabled": true,
              "type": "Text",
              "name": "grant_type",
              "value": "refresh_token"
            },
            {
              "enabled": true,
              "type": "Text",
              "name": "refresh_token",
              "value": "268bd8a7-bdd9-4173-9e1c-dfcf6e3e74b7"
            },
            {
              "enabled": true,
              "type": "Text",
              "name": "scope",
              "value": "all"
            }
          ]
        },
        "bodyType": "Form",
        "autoSetLength": true
      },
      "headersType": "Form",
      "uri": {
        "query": {
          "delimiter": "&",
          "items": []
        },
        "scheme": {
          "name": "http",
          "version": "V11"
        },
        "host": "localhost:9999",
        "path": "/oauth/token"
      },
      "id": "a749b2d2-d9e5-4304-8328-c4bd32919dbb",
      "lastModified": "2019-08-27T14:04:28.325+08:00",
      "name": "/oauth/token",
      "headers": [
        {
          "enabled": true,
          "name": "Content-Type",
          "value": "application/x-www-form-urlencoded"
        },
        {
          "enabled": true,
          "name": "Authorization",
          "value": "Basic YWRtaW46YWRtaW4="
        }
      ],
      "metaInfo": {},
      "parentId": "a47d90b2-4d72-459d-8777-73c3574b9f81"
    }
  ]
}
```