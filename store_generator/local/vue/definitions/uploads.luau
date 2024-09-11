local UploadS3 = require('vue/control/UploadS3')

local requests = require('vue/definitions/requests')

local uploads = {}

uploads.upload_guide_attachment = UploadS3
  :from_request(requests.request_presign_upload, 'guide_attachment')
  :with_bucket('app-public-document')
  :with_directory('guides')

return uploads