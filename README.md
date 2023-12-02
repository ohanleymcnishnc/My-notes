# My-notes
Documentation of problems encountered in the project.
GET expert/_search
{
  "query": {
    "match_all": {}
  },
  "_source": {
    "excludes": [
      "personalProfile",
      "educationalBackground",
      "workExperience",
      "bodyHtml",
      "bodyZh"
    ]
  }
}

GET article/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "articleId": {
              "value": "eb0ea2e7bb8365fff590759217fb0866"
            }
          }
        }
      ]
    }
  }
}
