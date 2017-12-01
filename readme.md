# Text2Json

Hi `@Marcus`, `:smile:` could you please send `@Nils` all information about the latest project? You can find more info at `https://abc.com`. `:highfive:`

The output JSON for the text above should look like this:

```json
{
  "emojis": [
    { "value": ":smile:" },
    { "value": ":highfive:" }
  ],
  "links": [
    { "title": "ABC Swipe", "url": "https://abc.com" }
  ],
  "mentions": [
    { "value": "@Marcus" },
    { "value": "@Nils" }
  ]
} 
```
When altering the text the output JSON should adjust accordingly. It should detect emojis (any A-Z, 0-9 or _ - (underscore, dash) characters written between a pair of colons e.g. :highfive:) as well es mentions (any A-Z, 0-9 or _ - (underscore, dash) following an @ (at)) and links (http/s) for which also the page's title should be retrieved from the link's corresponding HTML body.